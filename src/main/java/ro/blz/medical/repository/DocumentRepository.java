package ro.blz.medical.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.blz.medical.domain.PatientDocument;
import ro.blz.medical.dtos.DocumentDTO;

import java.util.List;

@Repository
public interface DocumentRepository extends ICatalogRepository<PatientDocument,Long> {

    @Query("""
select new ro.blz.medical.dtos.DocumentDTO(d.documentName,d.uploadDate) from PatientDocument d
where d.patient.ID=:id
""")
    public List<DocumentDTO> findByPatientId(Long id);
    public PatientDocument findByDocumentNameAndPatientID(String documentName, Long patient_ID);
}
