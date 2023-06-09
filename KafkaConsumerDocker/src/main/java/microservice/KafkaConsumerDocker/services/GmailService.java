package microservice.KafkaConsumerDocker.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

@Service
public class GmailService {
            private String myEmail="jj3564527@gmail.com";
            private Gmail service;

    GmailService() throws Exception{
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
         service = new Gmail.Builder(httpTransport,jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Gmail")
                .build();
    }
    private static Credential getCredentials(final NetHttpTransport httpTransport,GsonFactory jsonFactory )
            throws IOException {
        // Load client secrets.
        InputStream in = GmailService.class
                .getResourceAsStream(
           "/client_secret_126377207542-mgkopumi2nsgah58ianjcuv67ldts317.apps.googleusercontent.com.json"
                );
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " );
        }

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

     public void sendMail(String subject, String messageSt,String toEmail) throws Exception {

         // Encode as MIME message
         Properties props = new Properties();
         Session session = Session.getDefaultInstance(props, null);
         MimeMessage email = new MimeMessage(session);
         email.setFrom(new InternetAddress(myEmail));
         email.addRecipient(javax.mail.Message.RecipientType.TO,
                 new InternetAddress(toEmail));
         email.setSubject(subject);
         email.setText(messageSt);

         // Encode and wrap the MIME message into a gmail message
         ByteArrayOutputStream buffer = new ByteArrayOutputStream();
         email.writeTo(buffer);
         byte[] rawMessageBytes = buffer.toByteArray();
         String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
         Message message = new Message();
         message.setRaw(encodedEmail);

         try {
             // Create send message
             message = service.users().messages().send("me", message).execute();
             System.out.println("Message id: " + message.getId());
             System.out.println(message.toPrettyString());

         } catch (GoogleJsonResponseException e) {

             GoogleJsonError error = e.getDetails();
             if (error.getCode() == 403) {
                 System.err.println("Unable to send message: " + e.getDetails());
             } else {
                 throw e;
             }
         }

     }


}
