package ro.blz.medical.dtos;

import ro.blz.medical.domain.Patient;

import java.util.function.Function;

public class PatientDTOMapper implements Function<Patient, PatientDTO> {
    @Override
    public PatientDTO apply(Patient patient) {
        return PatientDTO.builder()
                .username(patient.getUsername())
                .role(patient.getRole())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .build();
    }


}
