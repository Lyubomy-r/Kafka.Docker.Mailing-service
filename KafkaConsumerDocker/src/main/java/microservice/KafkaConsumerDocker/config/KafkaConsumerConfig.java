package microservice.KafkaConsumerDocker.config;

import microservice.KafkaConsumerDocker.model.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

        @Value("${spring.kafka.consumer.bootstrap-servers}")
        private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, KafkaMessage> consumerFactory()
    {

        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"*");


        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<String, KafkaMessage>(config, new StringDeserializer(),
                new JsonDeserializer<>(KafkaMessage.class));
    }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String,KafkaMessage>bookListener()
        {
            ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory
                    = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());

            return factory;

        }

}
