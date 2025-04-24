package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.ConsultationRecord;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.dtos.ConsultationRecordDTO;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.exceptions.PatientNotFoundException;
import ro.blz.medical.repository.ConsultationRecordRepository;
import ro.blz.medical.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;
    private final ConsultationRecordRepository consultationRecordRepository;

    public List<Patient> findAll() {
        System.out.println("Find all patient called 1!");
        return patientRepository.findAll();
    }

    public PatientDTO findById(long id) {
        return patientRepository.findById(id).map(patientDTOMapper).orElseThrow(()-> new PatientNotFoundException("Patient with id "+ id + " not found"));
    }

    public List<PatientDTO> searchByCNPPhoneFnameLname(String searchWord){
        return patientRepository.
                findByCNPContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchWord)
                .stream()
                .map(patientDTOMapper).collect(Collectors.toList());
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
//        patient1.setCNP(patient.CNP());
        patient1.setPhone(patient.phone());
        patient1.setFirstName(patient.firstName());
        patient1.setLastName(patient.lastName());
        System.out.println(patient1);

        return patientDTOMapper.apply(patient1);
    }


    public List<ConsultationRecordDTO> getAllConsultationRecord(Long id) {
        return consultationRecordRepository.findBypatientId(id);
    }
}
