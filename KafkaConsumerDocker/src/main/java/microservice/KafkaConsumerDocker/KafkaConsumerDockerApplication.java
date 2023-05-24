package microservice.KafkaConsumerDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class KafkaConsumerDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerDockerApplication.class, args);
	}

}
