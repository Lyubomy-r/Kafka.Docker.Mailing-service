package microservice.KafkaConsumerDocker.services;

import microservice.KafkaConsumerDocker.controller.FeignController;
import microservice.KafkaConsumerDocker.model.CostumerEmailTable;
import microservice.KafkaConsumerDocker.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaListeners {

    private final GmailService gmailService;

    private final FeignController feignController;

    private final static String  TOPIC_NAME="${spring.kafka.topic.name}";

    private final static String  TOPIC_EMAIL_NAME ="${spring.kafka.topic.gmail.name}";

    @KafkaListener(topics = TOPIC_NAME,
                   groupId = "my-application", containerFactory = "bookListener")
    public void listener(KafkaMessage kafkaMessage) throws Exception {

        List<CostumerEmailTable> list1= feignController.showAllEmails();
        for(CostumerEmailTable email: list1){
            System.out.println(kafkaMessage + "- "+email.getEmail());
        }

    }

    @KafkaListener(topics = TOPIC_EMAIL_NAME,
            groupId = "my-application", containerFactory = "bookListener")
    public void emailListener(KafkaMessage data) throws Exception {

        List<CostumerEmailTable> list1= feignController.showAllEmails();
        for(CostumerEmailTable email: list1){
            System.out.println(data + email.getEmail());
            gmailService.sendMail("New message from kafka listener ", data.getMessage(), email.getEmail());
        }

    }






}
