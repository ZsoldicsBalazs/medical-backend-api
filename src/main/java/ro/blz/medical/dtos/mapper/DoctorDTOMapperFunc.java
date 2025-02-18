package ro.blz.medical.dtos.mapper;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.dtos.DoctorDTO;

import java.util.function.Function;

@Service
public class DoctorDTOMapperFunc implements Function<Doctor, DoctorDTO> {
    @Override
    public DoctorDTO apply(Doctor doctor) {
        return new DoctorDTO(
                doctor.getUser().getUsername(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getDepartment()
        );
    }
}
