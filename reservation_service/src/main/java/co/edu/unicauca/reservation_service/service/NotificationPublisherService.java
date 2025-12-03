package co.edu.unicauca.reservation_service.service;

import co.edu.unicauca.reservation_service.entity.Reservation;

public interface NotificationPublisherService {
    void sendWhatsappMessage(String to, Reservation reservation, String barberName, String services);
}
