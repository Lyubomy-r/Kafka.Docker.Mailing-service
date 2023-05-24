package microservice.KafkaProducerDocker.service;

import microservice.KafkaProducerDocker.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${spring.kafka.topic.name}")
    private String  TOPIC_NAME;

    @Value("${spring.kafka.topic.gmail.name}")
    private String  TOPIC_EMAIL_NAME;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public void sendMessage(KafkaMessage message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendMessageEmail(KafkaMessage message){
        kafkaTemplate.send(TOPIC_EMAIL_NAME, message);
    }
}
