package ro.blz.medical.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
public class ConsultationRecordDTO {
    private Long recordId;
    private Long appointmentId;
    private String diagnosis;
    private String results;
    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
    private LocalDateTime created_at;

    @Builder
    public ConsultationRecordDTO(Long recordId, Long appointmentId, String diagnosis, String results, Timestamp created_at) {
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.results = results;
        this.created_at = created_at.toLocalDateTime();
    }


    public ConsultationRecordDTO() {
    }
}

