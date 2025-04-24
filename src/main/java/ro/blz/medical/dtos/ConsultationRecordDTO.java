package ro.blz.medical.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter

public class ConsultationRecordDTO {
    private Long recordId;
    @NotNull
    private Long appointmentId;
    @NotNull
    private String diagnosis;
    @NotNull
    private String results;
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    private LocalDateTime created_at;
    private String drName;
    private String department;


    @Builder
    public ConsultationRecordDTO(Long recordId, Long appointmentId, String diagnosis, Timestamp created_at) {
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.created_at = created_at.toLocalDateTime();
    }

    public ConsultationRecordDTO(Long recordId, Long appointmentId, String diagnosis,String results, Timestamp created_at) {
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.results = results;
        this.created_at = created_at.toLocalDateTime();
    }
    public ConsultationRecordDTO(Long recordId, Long appointmentId, String diagnosis,String results, Timestamp created_at,String drName,String department) {
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.results = results;
        this.created_at = created_at.toLocalDateTime();
        this.drName = drName;
        this.department = department;
    }


    public ConsultationRecordDTO() {
    }
}

