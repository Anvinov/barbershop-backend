package co.edu.unicauca.reservation_service.infra.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WhatsappMessageDTO {
    private String to;
    private String message;
}
