package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Secretary;

import java.util.Optional;

@Repository
public interface SecretaryRepository extends ICatalogRepository<Secretary,Long> {
    public boolean existsByEmailEquals(String email);

    @Query(
            "SELECT s from Secretary s where s.user.user_id=:id"
    )
    public Optional<Secretary> findSecretaryByUserId(Long id);
}
