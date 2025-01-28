package ro.blz.medical.service;

import com.sun.source.tree.PackageTree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.repository.PatientRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    public Iterable<Patient> findAll() {
        return patientRepository.findAll();
    }
    public Optional<Patient> findById(long id) {
        return patientRepository.findById(id);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}
