package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.exceptions.PatientNotFoundException;
import ro.blz.medical.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(long id) {
        return patientRepository.findById(id);
    }

    public PatientDTO save(Patient patient) {

        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Pacient email already exists");
        }
        patientRepository.save(patient);
        return PatientDTO.builder()
                .email(patient.getEmail())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phone(patient.getPhone())
                .build();
    }

    @Transactional
    public PatientDTO update(PatientDTO patient){
        if (patient == null){
            throw new NullPointerException("Patient is null");
        }
        Patient patient1 = patientRepository.findById(patient.id()).orElseThrow(()-> new PatientNotFoundException(String.format("Patient with id %s not exists", patient.id())));
        patient1.setCNP(patient.CNP());
        patient1.setPhone(patient.phone());
        patient1.setFirstName(patient.firstName());
        patient1.setLastName(patient.lastName());
        return patientDTOMapper.apply(patient1);
    }
}
