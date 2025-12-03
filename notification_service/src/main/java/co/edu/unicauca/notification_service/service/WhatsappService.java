package co.edu.unicauca.notification_service.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.from}")
    private String from;

    public void sendMessage(String to, String body) {
        Twilio.init(accountSid, authToken);

        Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+57" + to),
                new com.twilio.type.PhoneNumber(from),
                body
        ).create();

        System.out.println("Message sent to whatsapp:+57" + to);
    }
}

