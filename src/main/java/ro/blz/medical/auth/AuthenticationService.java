package ro.blz.medical.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.DoctorRegistrationRequest;
import ro.blz.medical.domain.Role;
import ro.blz.medical.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final JwtService jwtService;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(DoctorRegistrationRequest registration) {
        var user = Doctor.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .phone(registration.phone())
                .role(Role.MEDIC)
                .department(registration.department())
                        .build();

        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    public AuthenticationResponse authenticate(DoctorAuthenticationRequest login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        var user = doctorRepository.findByEmail(login.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
