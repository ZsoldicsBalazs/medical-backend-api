package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.domain.Role;
import ro.blz.medical.dtos.BaseDTO;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.exceptions.EntityNotFoundException;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.PatientRepository;
import ro.blz.medical.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorDTOMapperFunc doctorDTOMapperFunc;
    private final PatientDTOMapper patientDTOMapper;
    private final UserService userService;

    public BaseDTO getProfile(Long id, Role role){

        switch (role) {
            case PATIENT -> {
                Patient patient = patientRepository.findByUserId(id).orElseThrow(()-> new EntityNotFoundException("Patient not found"));

                return patientDTOMapper.apply(patient);
            }
            case ADMIN, MEDIC -> {
                Doctor doctor = doctorRepository.findByUserId(id).orElseThrow(()-> new EntityNotFoundException("Doctor not found"));
                return doctorDTOMapperFunc.apply(doctor);
            }
            case SECRETARY -> {
                return null; // TODO: RETURN SECRETARY PROFILE
            }
        }
        throw new EntityNotFoundException("User not found");
    }

}
