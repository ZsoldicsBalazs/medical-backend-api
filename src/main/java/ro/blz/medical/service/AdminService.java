package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.DoctorRegistrationRequest;
import ro.blz.medical.auth.SecretaryRegistrationRequest;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.Role;
import ro.blz.medical.domain.Secretary;
import ro.blz.medical.domain.User;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.SecretaryDTO;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.SecretaryDTOMapper;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.SecretaryRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
        private final SecretaryRepository secretaryRepository;
        private final SecretaryDTOMapper secretaryDTOMapper;
        private final PasswordEncoder passwordEncoder;
        private final DoctorDTOMapperFunc doctorDTOMapperFunc;
        private final DoctorRepository doctorRepository;


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
        DoctorDTO newDoctorCreated = doctorDTOMapperFunc.apply(newDr);
//        DoctorDTO drdto = modelMapper.map(newDr,DoctorDTO.class);  Because of DoctorDTO is a record can not use modelMapper

        return newDoctorCreated;
    }


}
