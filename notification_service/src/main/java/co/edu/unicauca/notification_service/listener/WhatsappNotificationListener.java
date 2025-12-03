package co.edu.unicauca.notification_service.listener;

import co.edu.unicauca.notification_service.config.RabbitConfig;
import co.edu.unicauca.notification_service.dto.WhatsappMessageDTO;
import co.edu.unicauca.notification_service.service.WhatsappService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WhatsappNotificationListener {

    private final WhatsappService whatsappService;

    public WhatsappNotificationListener(WhatsappService whatsappService) {
        this.whatsappService = whatsappService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(WhatsappMessageDTO message) {
        System.out.println("Mensaje recibido: " + message);
        whatsappService.sendMessage(message.getTo(), message.getMessage());
    }
}