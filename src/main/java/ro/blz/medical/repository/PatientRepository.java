package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends ICatalogRepository<Patient,Long> {


    public boolean existsByEmail(String email);

    public default Optional<Patient> savePatient(Patient patient){
        return Optional.of(this.save(patient));
    }
    public Optional<Patient> findByCNPEqualsIgnoreCase(String cnp);
    public Optional<Patient> findByEmailIgnoreCase(String email);
    @Query("SELECT p FROM Patient p WHERE p.user.user_id = :id")
    public Optional<Patient> findByUserId(Long id);
}
