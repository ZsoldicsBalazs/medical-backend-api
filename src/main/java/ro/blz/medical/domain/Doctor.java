package ro.blz.medical.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Doctor extends BaseEntity<Long> {

    @Builder
    public Doctor(String firstName, String lastName, String phone, String email, String department, Double salary) {

        this.setEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
    }

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String department;
    private Double salary;

    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY)
    private User user;

}
