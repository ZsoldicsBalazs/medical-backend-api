package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.blz.medical.auth.UserRegistrationRequest;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.domain.Role;
import ro.blz.medical.domain.User;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.exceptions.DuplicateUserException;
import ro.blz.medical.repository.PatientRepository;
import ro.blz.medical.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PatientRegistrationService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PatientDTOMapper patientDTOMapper;

    @Transactional
    public PatientDTO registerPatient(UserRegistrationRequest registration) {
        var emailExists = userRepository.findByEmail(registration.email());
        var existingCNP = patientRepository.findByCNPEqualsIgnoreCase(registration.CNP());
        if (emailExists.isPresent() || existingCNP.isPresent()) {
            throw new DuplicateUserException("Patient with email or CNP already exists");
        }

        User newUser = User.builder()
                .username(registration.username())
                .password(passwordEncoder.encode(registration.password()))
                .email(registration.email())
                .role(Role.PATIENT)
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


        return patientDTOMapper.apply(newPatient);
    }
}
