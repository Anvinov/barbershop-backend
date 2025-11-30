package co.edu.unicauca.reservation_service.repository.converter;

import co.edu.unicauca.reservation_service.entity.state.ReservationState;
import co.edu.unicauca.reservation_service.factory.ReservationStateFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReservationStateConverter implements AttributeConverter<ReservationState, String> {

    @Override
    public String convertToDatabaseColumn(ReservationState state) {
        // Aquí decides qué guardar en la BD
        return state != null ? state.getStatus() : null;
    }

    @Override
    public ReservationState convertToEntityAttribute(String dbValue) {
        // Aquí reconstruyes el state según el valor guardado
        return ReservationStateFactory.from(dbValue);
    }
}
