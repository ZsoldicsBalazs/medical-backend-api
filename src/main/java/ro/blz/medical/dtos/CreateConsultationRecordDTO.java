package ro.blz.medical.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CreateConsultationRecordDTO(
         Long recordId,
         Long appointmentId,
         String diagnosis,
         String results,
         LocalDateTime created_at,
         String drName,
         String department

) {
}
