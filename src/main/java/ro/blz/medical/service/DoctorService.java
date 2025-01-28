package ro.blz.medical.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.DoctorRegistrationRequest;
import ro.blz.medical.domain.Role;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.DoctorDTOMapper;
import ro.blz.medical.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapper doctorDTOMapper;


    public DoctorDTO getDoctorById(long id) {
        return doctorRepository.findById(id).map(doctorDTOMapper).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorDTOMapper).collect(Collectors.toList());
    }

    public void save(DoctorRegistrationRequest request) {


        if (doctorRepository.existsByEmailIsContainingIgnoreCase(request.email())) {
            throw new RuntimeException("Email already exists");
        }
        Doctor newDoctor = new Doctor(
                request.username(),
                request.password(),
                Role.MEDIC,
                request.firstName(),
                request.lastName(),
                request.phone(),
                request.email(),
                request.department(),
                0.00
        );

        doctorRepository.save(newDoctor);

    }

    public Optional<Doctor> findByEmail(String email) {
        return Optional.ofNullable(doctorRepository.findByEmailIsContainingIgnoreCase(email));
    }

}
