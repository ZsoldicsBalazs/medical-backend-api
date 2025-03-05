package ro.blz.medical.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.domain.User;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.exceptions.EntityNotFoundException;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.PatientRepository;
import ro.blz.medical.repository.UserRepository;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorDTOMapperFunc doctorDTOMapperFunc;
    private final PatientDTOMapper patientDTOMapper;

    public ProfileController(UserRepository userRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, DoctorDTOMapperFunc doctorDTOMapperFunc, PatientDTOMapper patientDTOMapper) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.doctorDTOMapperFunc = doctorDTOMapperFunc;
        this.patientDTOMapper = patientDTOMapper;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(()-> new EntityNotFoundException("User not found"));

        switch (user.getRole()) {
            case PATIENT -> {
                Patient patient = patientRepository.findByUserId(user.getUser_id()).orElseThrow(()-> new EntityNotFoundException("Patient not found"));

                return ResponseEntity.ok(patientDTOMapper.apply(patient));
            }
            case ADMIN, MEDIC -> {
                Doctor doctor = doctorRepository.findByUserId(user.getUser_id()).orElseThrow(()-> new EntityNotFoundException("Doctor not found"));
                return new ResponseEntity<>(doctorDTOMapperFunc.apply(doctor), HttpStatus.OK);
            }
            case SECRETARY -> {
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
