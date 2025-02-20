package ro.blz.medical.dtos.mapper;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Patient;
import ro.blz.medical.domain.User;
import ro.blz.medical.dtos.PatientDTO;

import java.util.function.Function;

@Service
public class PatientDTOMapper implements Function<Patient, PatientDTO> {
    @Override
    public PatientDTO apply(Patient patient) {

        return PatientDTO.builder()
                .id(patient.getID())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .CNP(patient.getCNP())
                .build();
    }


}
