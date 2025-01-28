package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends ICatalogRepository<Patient,Long> {

    public Optional<Patient> findByUsername(String username);

}
