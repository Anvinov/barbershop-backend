package co.edu.unicauca.reservation_service.service;

import co.edu.unicauca.reservation_service.config.RabbitConfig;
import co.edu.unicauca.reservation_service.infra.dto.notification.WhatsappMessageDTO;
import co.edu.unicauca.reservation_service.entity.Reservation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationPublisherServiceImpl implements NotificationPublisherService {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = Logger.getLogger(NotificationPublisherServiceImpl.class.getName());

    public NotificationPublisherServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendWhatsappMessage(String to, Reservation reservation, String barberName, String services) {
        WhatsappMessageDTO message = new WhatsappMessageDTO();

        message.setTo(to);

        message.setMessage(String.format(
                "¡Tu reserva fue creada exitosamente! 🎉\n\n" +
                        "📅 Fecha: %s\n" +
                        "⏰ Hora: %s - %s\n" +
                        "💈 Barbero: %s\n" +
                        "✂️ Servicios: \n" +
                        "%s\n" +
                        "¡Gracias por confiar en nosotros!",
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                barberName,
                services
        ));

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, message);

        logger.log(Level.INFO, "Notification send successfully");
    }
}
