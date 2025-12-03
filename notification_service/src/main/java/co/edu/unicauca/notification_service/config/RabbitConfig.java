package co.edu.unicauca.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE = "whatsapp_notifications";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}