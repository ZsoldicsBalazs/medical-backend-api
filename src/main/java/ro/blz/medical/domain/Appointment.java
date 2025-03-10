package ro.blz.medical.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Appointment extends BaseEntity<Long>{

    @Setter
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Setter
    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentSatus status;

    @OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL, orphanRemoval = true)
    private ConsultationRecord consultationRecord;

    public Appointment(AppointmentSatus status, Patient patient, Doctor doctor, LocalDate appointmentDate,LocalTime appointmentTime) {
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
    }

    public void setConsultationRecord(ConsultationRecord consultationRecord) {
        this.consultationRecord = consultationRecord;
        if (consultationRecord != null) {
            consultationRecord.setAppointment(this);
        }
    }
}
