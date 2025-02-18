package ro.blz.medical.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Doctor extends BaseEntity<Long> {

    @Builder
    public Doctor(String firstName, String lastName, String phone, String email, String department, Double salary,User user) {

        this.setEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
        this.user = user;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
