package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends ICatalogRepository<Doctor, Long> {
    public Doctor findByEmailIsContainingIgnoreCase(String email);

    public boolean existsByEmailIsContainingIgnoreCase(String email);
    @Query("SELECT d FROM Doctor d WHERE d.user.user_id=:userid")
    public Optional<Doctor> findByUserId(Long userid);
}
