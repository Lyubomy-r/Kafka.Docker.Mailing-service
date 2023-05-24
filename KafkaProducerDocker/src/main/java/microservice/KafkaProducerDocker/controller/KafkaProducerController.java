package microservice.KafkaProducerDocker.controller;

import microservice.KafkaProducerDocker.model.KafkaMessage;
import microservice.KafkaProducerDocker.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage message){
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/messageEmail")
    public ResponseEntity<String> sendMessageEmail(@RequestBody KafkaMessage message){
        kafkaProducerService.sendMessageEmail(message);
        return ResponseEntity.ok("Message sent successfully");
    }
}
