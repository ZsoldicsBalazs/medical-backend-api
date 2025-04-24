package ro.blz.medical.service;

import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Appointment;
import ro.blz.medical.domain.ConsultationRecord;
import ro.blz.medical.dtos.ConsultationRecordDTO;
import ro.blz.medical.repository.AppointmentRepository;
import ro.blz.medical.repository.ConsultationRecordRepository;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class ConsultationRecordService {
    private final ConsultationRecordRepository consultationRecordRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public ConsultationRecordDTO createConsultationRecord(ConsultationRecordDTO recordDTO){
        if (recordDTO!=null){
            Appointment appointment = appointmentRepository.getReferenceById(recordDTO.getRecordId());

            ConsultationRecord record =
                    ConsultationRecord.builder()
                            .appointment(appointment)
                            .diagnosis(recordDTO.getDiagnosis())
                            .results(recordDTO.getResults())
                            .build();
            consultationRecordRepository.save(record);

//            TODO: MAKE A RECORD DTO MAPPER
            ConsultationRecordDTO dto =  new ConsultationRecordDTO(record.getID(), recordDTO.getAppointmentId(), recordDTO.getDiagnosis(), recordDTO.getResults(), Timestamp.valueOf(record.getCreated_at()));

            ConsultationRecordDTO.builder()
                    .recordId(record.getID())
                    .appointmentId(appointment.getID())
                    .diagnosis(record.getDiagnosis())
                    .results(record.getResults())
                    .build();
            return dto;
        }
        return null;
    }

}
