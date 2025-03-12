package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.dtos.AppointmentDetailsDTO;

import java.util.List;

public interface AppointmentRepository extends ICatalogRepository<Appointment,Long> {

//    @Query(value = """
//            select a.id, d.first_name as doctor_name,d.last_name as doctor_lastname,d.department,a.appointment_date,a.appointment_time,a.status
//            from appointments as a
//            left join doctor as d on a.doctor_id=d.id
//            where a.patient_id=?1""",
//            nativeQuery = true)
//    public List<AppointmentDetailsDTO> getAppointmentByPatientId(Long patientId);

    @Query("""
    select new ro.blz.medical.dtos.AppointmentDetailsDTO(
        a.ID,
        d.firstName,
        d.lastName,
        d.department,
        a.appointmentDate,
        a.appointmentTime,
        a.status)
    from Appointment a
    left join a.doctor d
    where a.patient.ID = ?1
""")
    public List<AppointmentDetailsDTO> getAppointmentByPatientId(Long patientId);



    @Query(value = "select a.id,a.doctor_id, a.patient_id, a.status, a.appointment_date, a.appointment_time FROM appointments a", nativeQuery = true)
    public List<Object[]> findAllAppointment();

}
