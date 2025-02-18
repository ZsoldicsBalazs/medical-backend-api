package ro.blz.medical.repository;

import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Secretary;

@Repository
public interface SecretaryRepository extends ICatalogRepository<Secretary,Long> {
    public boolean existsByEmailEquals(String email);
}
