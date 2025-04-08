package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.DoctorProcedure;

import java.util.List;

@Repository
public interface DoctorProcedureRepository extends JpaRepository<DoctorProcedure, Long> {

    @Query(value = """
        SELECT d.id, d.doctor_id, d.procedure_id,d.price\s
        FROM doctor_procedure d\s
        WHERE d.doctor_id = ?1""",
            nativeQuery = true)
    public List<DoctorProcedure> findByDoctorId(Long id);

    @Query("SELECT dp FROM DoctorProcedure dp " +
            "LEFT JOIN FETCH dp.doctor " +
            "LEFT JOIN FETCH dp.procedure " +
            "WHERE dp.doctor.ID = ?1 AND dp.procedure.ID = ?2")
    public DoctorProcedure findByDrAndProcedureId(Long id, Long procedureId);

}
