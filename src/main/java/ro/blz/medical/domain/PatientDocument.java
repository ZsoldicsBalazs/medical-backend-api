package ro.blz.medical.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "patient_files")
@Getter
@Setter
public class PatientDocument extends BaseEntity<Long> {

    // Relație Many-to-One cu Patient
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;

    @Column(name = "document_name", nullable = true)
    private String documentName;

    public PatientDocument() {}

    public PatientDocument(Patient patient, String filePath, LocalDateTime uploadDate, String documentName) {
        this.patient = patient;
        this.filePath = filePath;
        this.uploadDate = uploadDate;
        this.documentName = documentName;
    }
}
