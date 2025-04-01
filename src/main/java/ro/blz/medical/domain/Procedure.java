package ro.blz.medical.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medical_procedure")
public class Procedure extends BaseEntity<Long>{
    @Column(name = "drg_code")
    private String DRGcode;
    private String description;

}
