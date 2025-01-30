package ro.blz.medical.dtos;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Patient;

import java.util.function.Function;

@Service
public class PatientDTOMapper implements Function<Patient, PatientDTO> {
    @Override
    public PatientDTO apply(Patient patient) {
        var username = patient.getUser() == null ? "" : patient.getUser().getUsername();
        return PatientDTO.builder()
                .email(patient.getEmail())
                .username(username)
                .phone(patient.getPhone())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .CNP(patient.getCNP())
                .build();
    }


}
