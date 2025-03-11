package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.dtos.AppointmentDTO;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.repository.AppointmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public List<AppointmentDTO> findAllAppointment() {
        return appointmentRepository.findAllAppointment().stream().map(AppointmentDTO::fromRaw).collect(Collectors.toList());
    }

    public List<AppointmentDetailsDTO> getAppointmentDetailsByPatientID(long id){
        System.out.println(id);
        return appointmentRepository.getAppointmentByPatientId(id);
    }
}
