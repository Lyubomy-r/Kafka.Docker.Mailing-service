package microservice.KafkaProducerDocker.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage {

    private String firstName;

    private String lastName;

    private Integer age;

    private String message;
}
