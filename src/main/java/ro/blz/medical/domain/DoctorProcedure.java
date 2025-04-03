package ro.blz.medical.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@Entity
@Getter
@Setter
@Table(name = "doctor_procedure")
public class DoctorProcedure extends BaseEntity<Long>{

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "procedure_id",nullable = false)
    private Procedure procedure;

    @Column(name = "price")
    private Double price;
}
