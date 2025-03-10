package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.dtos.AppointmentDTO;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.dtos.mapper.AppointmentDTOMapperFunc;
import ro.blz.medical.repository.AppointmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDTOMapperFunc appointmentDTOMapperFunc;

    public List<AppointmentDTO> findAllAppointment() {
//        MOCK DATA LOADED
//        Doctor doctor = entityManager.getReference(Doctor.class,5);
//        Patient patient = entityManager.getReference(Patient.class,233);
//
//        appointmentRepository.save(new Appointment(AppointmentSatus.SCHEDULED, patient,doctor, LocalDate.now(), LocalTime.of(22,25) ));

        return appointmentRepository.findAll().stream().map(appointmentDTOMapperFunc).toList();
    }

    public List<AppointmentDetailsDTO> getAppointmentDetailsByPatientID(long id){
        System.out.println(id);
        return appointmentRepository.getAppointmentByPatientId(id);
    }
}
