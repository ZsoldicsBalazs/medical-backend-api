package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.dtos.AppointmentDetailsDTO;

import java.util.List;

public interface AppointmentRepository extends ICatalogRepository<Appointment,Long> {

    @Query(value = "select a.id, d.first_name as doctor_name,d.last_name as doctor_lastname,d.department,a.appointment_date,a.appointment_time " +
            "from appointments as a\n" +
            "left join doctor as d on a.doctor_id=d.id " +
            "where a.patient_id=?1",
            nativeQuery = true)
    public List<AppointmentDetailsDTO> getAppointmentByPatientId(Long patientId);
}
