package co.edu.unicauca.reservation_service.RabbitMQ.config;

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

