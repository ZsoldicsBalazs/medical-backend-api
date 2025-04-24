package ro.blz.medical.service;

import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.domain.ConsultationRecord;
import ro.blz.medical.dtos.ConsultationRecordDTO;
import ro.blz.medical.repository.AppointmentRepository;
import ro.blz.medical.repository.ConsultationRecordRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConsultationRecordService {
    private final ConsultationRecordRepository consultationRecordRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public ConsultationRecordDTO createConsultationRecord(ConsultationRecordDTO recordDTO){

        Assert.notNull(recordDTO, "ConsultationRecordDTO must not be null");
        System.out.println("After assertion not null");
            Appointment appointment = appointmentRepository.getReferenceById(recordDTO.getRecordId());
        System.out.println("AFter referencebyID");

            ConsultationRecord record =
                            ConsultationRecord.builder()
                            .appointment(appointment)
                            .diagnosis(recordDTO.getDiagnosis())
                            .results(recordDTO.getResults())
                            .created_at(LocalDateTime.now())
                            .build();

            consultationRecordRepository.save(record);

//            TODO: MAKE A RECORD DTO MAPPER ------- RETURNING OPTIONAL MAYBE ?
            ConsultationRecordDTO dto =  new ConsultationRecordDTO(record.getID(), recordDTO.getAppointmentId(), recordDTO.getDiagnosis(), Timestamp.valueOf(record.getCreated_at()));

            return dto;
    }

}
