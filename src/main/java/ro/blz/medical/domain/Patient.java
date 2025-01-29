package ro.blz.medical.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Patient extends BaseEntity<Long> {

    @Builder
    public Patient(String email, String phone, String firstName, String lastName) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY)
    private User user;




}
