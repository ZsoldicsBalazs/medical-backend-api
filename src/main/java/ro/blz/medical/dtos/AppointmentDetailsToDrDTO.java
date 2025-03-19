package ro.blz.medical.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import ro.blz.medical.domain.AppointmentSatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDetailsToDrDTO(
        long id,
        String patientName,
        String patientFirstName,
        LocalDate appointmentDate,
        @JsonFormat(pattern = "HH:mm")
        LocalTime appointmentTime,
        AppointmentSatus status) {
}
