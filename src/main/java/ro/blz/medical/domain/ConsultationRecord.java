package ro.blz.medical.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultation_records")
@AllArgsConstructor
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



}
