package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.blz.medical.domain.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface ICatalogRepository<T extends BaseEntity<ID>,ID extends Serializable> extends JpaRepository<T, ID> {

}
