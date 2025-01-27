package ro.blz.medical.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.DoctorRegistrationRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {



    public AuthenticationResponse register(DoctorRegistrationRequest registration) {
        var user = Doctor.builder()
                .username(registration.username())
                .password(registration.password())
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .phone(registration.phone())
                .department(registration.department())
                ;

        return null;
    }

    public AuthenticationResponse authenticate(DoctorAuthenticationRequest login) {
        return null;
    }
}
