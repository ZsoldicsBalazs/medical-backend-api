package ro.blz.medical.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultation_records")
@NoArgsConstructor
@Getter
@Setter
public class ConsultationRecord extends BaseEntity<Long> {

    // Legătură 1:1 cu appointment-ul corespunzător
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(name = "diagnosis", length = 2000)
    private String diagnosis;

    @Column(name = "results", length = 2000)
    private String results;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Builder
    public ConsultationRecord(Appointment appointment, String diagnosis, String results) {
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.results = results;
        this.created_at = LocalDateTime.now();
    }


    public ConsultationRecord(Appointment appointment, String diagnosis, String results, LocalDateTime created_at) {
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.results = results;
        this.created_at = created_at;
    }
}
