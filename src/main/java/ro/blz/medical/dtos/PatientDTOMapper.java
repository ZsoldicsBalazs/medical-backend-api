package ro.blz.medical.dtos;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Patient;

import java.util.function.Function;

@Service
public class PatientDTOMapper implements Function<Patient, PatientDTO> {
    @Override
    public PatientDTO apply(Patient patient) {
        return PatientDTO.builder()
                .email(patient.getEmail())
                .username(patient.getUser().getUsername())
                .phone(patient.getPhone())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .build();
    }


}
