package ro.blz.medical.repository;

import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.Procedure;
@Repository
public interface ProcedureRepository extends ICatalogRepository<Procedure,Long> {

    public Procedure findByName(String name);

}
