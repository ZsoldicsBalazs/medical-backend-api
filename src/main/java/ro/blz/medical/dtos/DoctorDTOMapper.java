package ro.blz.medical.dtos;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;

import java.util.function.Function;

@Service
public class DoctorDTOMapper implements Function<Doctor, DoctorDTO> {
    @Override
    public DoctorDTO apply(Doctor doctor) {
        return new DoctorDTO(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getDepartment()
        );
    }
}
