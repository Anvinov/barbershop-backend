package co.edu.unicauca.barber_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    @OneToOne
    @JoinColumn(name = "barber_id", unique = true, nullable = false)
    private Barber barber;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> slots;

    private List<String> workDays;

    public void addSlot(TimeSlot timeSlot){
        slots.add(timeSlot);
    }

    public void removeSlot(TimeSlot timeSlot){
        slots.remove(timeSlot);
    }
}
