package ro.blz.medical.repository;

import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Doctor;

@Repository
public interface DoctorRepository extends ICatalogRepository<Doctor, Long> {
    public Doctor findByEmailIsContainingIgnoreCase(String email);

    public boolean existsByEmailIsContainingIgnoreCase(String email);

}
