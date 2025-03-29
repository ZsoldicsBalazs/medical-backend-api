package ro.blz.medical.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.dtos.AppointmentDetailsToDrDTO;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends ICatalogRepository<Appointment,Long> {

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
    @Query("""
    select new ro.blz.medical.dtos.AppointmentDetailsToDrDTO(
        a.ID,
        p.firstName,
        p.lastName,
        a.appointmentDate,
        a.appointmentTime,
        a.status)
    from Appointment a
    left join a.patient p
    where a.doctor.ID = ?1
""")
    public List<AppointmentDetailsToDrDTO> getAppointmentByDoctorId(Long drId);

    @Query(value = "select a.id,a.doctor_id, a.patient_id, a.status, a.appointment_date, a.appointment_time FROM appointments a", nativeQuery = true)
    public List<Object[]> findAllAppointment();

    @Query("""
            SELECT new ro.blz.medical.dtos.AppointmentDetailsToDrDTO(
                    a.ID,
                    a.patient.firstName,
                    a.patient.lastName,
                    a.appointmentDate,
                    a.appointmentTime,
                    a.status) \
            FROM Appointment a \
            WHERE \
            a.doctor.ID = :doctorId AND \
            (COALESCE(:cnp, '') = '' OR a.patient.CNP LIKE %:cnp%) AND \
            (COALESCE(:patientName, '') = '' OR a.patient.lastName LIKE %:patientName%) AND \
            (COALESCE(:patientFirstName, '') = '' OR a.patient.firstName LIKE %:patientFirstName%)""")
    public List<AppointmentDetailsToDrDTO> getAppointmentsByCriteriaWithoutDate(
            @Param("doctorId")long doctorId,
            @Param("cnp") String cnp,
            @Param("patientName") String patientName,
            @Param("patientFirstName") String patientFirstName);
    @Query("""
            SELECT new ro.blz.medical.dtos.AppointmentDetailsToDrDTO(
                    a.ID,
                    a.patient.firstName,
                    a.patient.lastName,
                    a.appointmentDate,
                    a.appointmentTime,
                    a.status) \
            FROM Appointment a \
            WHERE \
            a.doctor.ID = :doctorId AND \
            (COALESCE(:cnp, '') = '' OR a.patient.CNP LIKE %:cnp%) AND \
            (COALESCE(:patientName, '') = '' OR LOWER(a.patient.lastName) LIKE LOWER(CONCAT('%', :patientName, '%'))) AND \
            (COALESCE(:patientFirstName, '') = '' OR LOWER(a.patient.firstName) LIKE LOWER(CONCAT('%', :patientFirstName, '%'))) AND \
            (:appointmentDate IS NULL OR a.appointmentDate = CAST(:appointmentDate AS date))""")
    public List<AppointmentDetailsToDrDTO> getAppointmentsByCriteriaWithDate(
            @Param("doctorId")long doctorId,
            @Param("cnp") String cnp,
            @Param("patientName") String patientName,
            @Param("patientFirstName") String patientFirstName,
            @Param("appointmentDate") String appointmentDate);
}
