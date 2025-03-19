package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.blz.medical.domain.AppointmentSatus;
import ro.blz.medical.dtos.AppointmentDTO;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.dtos.AppointmentDetailsToDrDTO;
import ro.blz.medical.exceptions.AppointmentNotFoundException;
import ro.blz.medical.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.Comparator;
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
        return appointmentRepository.getAppointmentByPatientId(id).stream().sorted(Comparator.comparing(AppointmentDetailsDTO::appointmentDate).reversed()).collect(Collectors.toList());
    }
    @Transactional
    public List<AppointmentDetailsToDrDTO> getAppointmentDetailsByDoctor(long id, String cnp,String patientName, String patientFirstName,String appointmentDate){
        var appointmentList = appointmentRepository.getAppointmentByDoctorId(id).stream().sorted(Comparator.comparing(AppointmentDetailsToDrDTO::appointmentDate).reversed()).collect(Collectors.toList());
        List<AppointmentDetailsToDrDTO> appointmentList2;
        if (appointmentDate == null){
            System.out.println("Date = null !");
             appointmentList2 = appointmentRepository.getAppointmentsByCriteriaWithoutDate(id,cnp,patientName,patientFirstName).stream().sorted(Comparator.comparing(AppointmentDetailsToDrDTO::appointmentDate).reversed()).toList();
        }else {
             appointmentList2 = appointmentRepository.getAppointmentsByCriteriaWithDate(id,cnp,patientName,patientFirstName, appointmentDate).stream().sorted(Comparator.comparing(AppointmentDetailsToDrDTO::appointmentDate).reversed()).toList();
            System.out.println("DATE == "+ appointmentDate);
        }



//        if(appointmentList2.isEmpty()){
//
//            throw new AppointmentNotFoundException("Appointment for Doctor " + id + " not found");
//        }
        return appointmentList2;
    }

    @Transactional
    public void cancelAppointment(long id){
        var appointment = appointmentRepository.findById(id).orElseThrow(()-> new AppointmentNotFoundException("Appointment with "+ id +" not found"));
        appointment.setStatus(AppointmentSatus.CANCELLED);
    }
}
