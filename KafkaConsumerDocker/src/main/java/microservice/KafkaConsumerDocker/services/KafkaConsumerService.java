package microservice.KafkaConsumerDocker.services;

import microservice.KafkaConsumerDocker.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final String  TOPIC_NAME="${spring.kafka.topic.name}";

    private final String  TOPIC_EMAIL_NAME ="${spring.kafka.topic.gmail.name}";

    @KafkaListener(topics = TOPIC_NAME,
            groupId = "my-application", containerFactory = "bookListener")
    public KafkaMessage listener(KafkaMessage kafkaMessage) {

            System.out.println(kafkaMessage + "- ");
            return kafkaMessage;

    }

    @KafkaListener(topics = TOPIC_EMAIL_NAME,
            groupId = "my-application", containerFactory = "bookListener")
    public KafkaMessage emailListener(KafkaMessage data){

        System.out.println(data.getMessage() );
        return data;

        }




}
