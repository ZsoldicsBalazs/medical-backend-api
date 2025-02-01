package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.DoctorRegistrationRequest;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.auth.UserRegistrationRequest;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.domain.*;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationResponse registerPatient(UserRegistrationRequest registration) {
        var emailExists = userRepository.findByUsername(registration.username());
        if (emailExists.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Patient newPatient = Patient.builder()
                .email(registration.email())
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .phone(registration.phone())
                .CNP(registration.CNP())
                .build();

        User newUser = User.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .email(registration.email())
                .role(Role.PACIENT)
                .patient(newPatient)
                .build();
        newPatient.setUser(newUser);

        userRepository.save(newUser);

        var jwtToken = jwtService.generateToken(newUser);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }

    public DoctorDTO registerDoctor(DoctorRegistrationRequest registration) {

        Doctor newDr = Doctor.builder()
                .phone(registration.phone())
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .salary(0.00)
                .department(registration.department())
                .build();

        User newUser = User.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .email(registration.email())
                .doctor(newDr)
                .role(Role.MEDIC)
                .build();

        newDr.setUser(newUser);


        userRepository.save(newUser);
        DoctorDTO drdto = modelMapper.map(newDr,DoctorDTO.class);
//
//        var jwtToken = jwtService.generateToken(newUser);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//        authenticationResponse.setToken(jwtToken);
        return drdto;
    }

    public AuthenticationResponse authenticate(UserAuthenticationRequest login) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        System.out.println("DR AUth login getemail : " + login.getEmail());
        System.out.println("DR AUth login password : " + login.getPassword());
        var user = userRepository.findByUsername(login.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
