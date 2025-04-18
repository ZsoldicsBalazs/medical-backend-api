package ro.blz.medical.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Patient extends BaseEntity<Long> {

    @Column(nullable = false)
    private String firstName;
    @Column(unique = true, length = 13)
    private String CNP;
    @Column(nullable = false)
    private String lastName;
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Builder
    public Patient(long id,String email, String phone, String firstName, String lastName, String CNP, User user) {
        this.email = email;
        this.user = user;
        this.CNP = CNP;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                "} " + super.toString();
    }
}
