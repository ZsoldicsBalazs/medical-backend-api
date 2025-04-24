package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import ro.blz.medical.domain.ConsultationRecord;
import ro.blz.medical.dtos.ConsultationRecordDTO;

import java.util.List;

public interface ConsultationRecordRepository extends ICatalogRepository<ConsultationRecord,Long>{

    @Query(value = """
SELECT cr.id,cr.appointment_id,cr.diagnosis,cr.results,cr.created_at
FROM consultation_records cr
LEFT JOIN appointments ap ON cr.appointment_id=ap.id
LEFT JOIN patient p ON ap.patient_id=p.id
where p.id= :patientId
""",nativeQuery = true)
    public List<ConsultationRecordDTO> findBypatientId(Long patientId);

}
