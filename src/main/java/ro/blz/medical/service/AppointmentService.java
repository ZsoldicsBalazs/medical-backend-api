package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blz.medical.domain.AppointmentSatus;
import ro.blz.medical.dtos.AppointmentDTO;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.exceptions.AppointmentNotFoundException;
import ro.blz.medical.repository.AppointmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public List<AppointmentDTO> findAllAppointment() {
        return appointmentRepository.findAllAppointment().stream().map(AppointmentDTO::fromRaw).collect(Collectors.toList());
    }

    @Transactional
    public List<AppointmentDetailsDTO> getAppointmentDetailsByPatientID(long id){
        return appointmentRepository.getAppointmentByPatientId(id);
    }

    @Transactional
    public void cancelAppointment(long id){
        var appointment = appointmentRepository.findById(id).orElseThrow(()-> new AppointmentNotFoundException("Appointment with "+ id +" not found"));
        appointment.setStatus(AppointmentSatus.CANCELLED);
    }
}
