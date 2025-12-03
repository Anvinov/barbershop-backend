package co.edu.unicauca.reservation_service.RabbitMQ.service;

import co.edu.unicauca.reservation_service.RabbitMQ.config.RabbitConfig;
import co.edu.unicauca.reservation_service.RabbitMQ.dto.WhatsappMessageDTO;
import co.edu.unicauca.reservation_service.infra.dto.reservation.response.ReservationResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationPublisherService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendWhatsappMessage(String to, ReservationResponseDTO request, String barberName, String services) {
        WhatsappMessageDTO message = new WhatsappMessageDTO();

        message.setTo(to);

        message.setMessage(String.format(
                "¡Tu reserva fue creada exitosamente! 🎉\n\n" +
                        "📅 Fecha: %s\n" +
                        "⏰ Hora: %s - %s\n" +
                        "💈 Barbero: %s\n" +
                        "✂️ Servicios: \n" +
                        "%s\n\n" +
                        "¡Gracias por confiar en nosotros!",
                request.getDate(),
                request.getStartTime(),
                request.getEndTime(),
                barberName,
                services
        ));

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, message);
    }
}
