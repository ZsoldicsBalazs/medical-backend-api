package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.*;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.domain.*;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.SecretaryDTO;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.SecretaryDTOMapper;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.PatientRepository;
import ro.blz.medical.repository.SecretaryRepository;
import ro.blz.medical.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorDTOMapperFunc dtoMapperFunc;
    private final SecretaryRepository secretaryRepository;
    private final SecretaryDTOMapper secretaryDTOMapper;

    public AuthenticationResponse registerPatient(UserRegistrationRequest registration) {
        var emailExists = userRepository.findByEmail(registration.email());
        if (emailExists.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User newUser = User.builder()
                    .username(registration.username())
                    .password(passwordEncoder.encode(registration.password()))
                    .email(registration.email())
                    .role(Role.PACIENT)
                    .build();

        Patient newPatient = Patient.builder()
                    .email(registration.email())
                    .firstName(registration.firstName())
                    .lastName(registration.lastName())
                    .phone(registration.phone())
                    .CNP(registration.CNP())
                    .user(newUser)
                    .build();
            patientRepository.save(newPatient);


        var jwtToken = jwtService.generateToken(newUser);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
    @Transactional
    public DoctorDTO registerDoctor(DoctorRegistrationRequest registration) {

        User newUser = User.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .email(registration.email())
                .role(Role.MEDIC)
                .build();

        Doctor newDr = Doctor.builder()
                .phone(registration.phone())
                .firstName(registration.firstName())
                .lastName(registration.lastName())
                .email(registration.email())
                .salary(0.00)
                .user(newUser)
                .department(registration.department())
                .build();

        doctorRepository.save(newDr);
        DoctorDTO newDoctorCreated = dtoMapperFunc.apply(newDr);
//        DoctorDTO drdto = modelMapper.map(newDr,DoctorDTO.class);  Because of DoctorDTO is a record can not use modelMapper

        return newDoctorCreated;
    }

    @Transactional
    public SecretaryDTO registerSecretary(SecretaryRegistrationRequest registration){
        if (secretaryRepository.existsByEmailEquals(registration.email())){
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .role(Role.SECRETARY)
                .email(registration.email())
                .build();

        Secretary secretary = new Secretary(
                registration.firstName(),
                registration.lastName(),
                registration.email(),
                registration.phoneNumber(),
                registration.salary(),
                user
        );
        secretaryRepository.save(secretary);
        return secretaryDTOMapper.apply(secretary);
    }
    public AuthenticationResponse authenticate(UserAuthenticationRequest login) {
//        User user = userRepository.findByEmail(login.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("user: " + user);
        var user = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        System.out.println("DR AUth login getemail : " + login.getEmail());
        System.out.println("DR AUth login password : " + login.getPassword());
        var jwtToken = jwtService.generateToken((User) user.getPrincipal());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);
        return authenticationResponse;
    }
}
