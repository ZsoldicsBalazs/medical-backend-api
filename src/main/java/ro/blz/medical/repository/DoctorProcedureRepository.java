package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.DoctorProcedure;

@Repository
public interface DoctorProcedureRepository extends JpaRepository<DoctorProcedure, Integer> {

}
