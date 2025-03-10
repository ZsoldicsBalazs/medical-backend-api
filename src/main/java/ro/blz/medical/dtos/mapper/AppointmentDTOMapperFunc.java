package ro.blz.medical.dtos.mapper;

import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.dtos.AppointmentDTO;

import java.util.function.Function;

@Service
public class AppointmentDTOMapperFunc implements Function<Appointment, AppointmentDTO> {
    @Override
    public AppointmentDTO apply(Appointment appointment) {
        return new AppointmentDTO(appointment.getID(),
                appointment.getDoctor().getID(),
                appointment.getPatient().getID(),
                appointment.getStatus(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime());

    }
}
