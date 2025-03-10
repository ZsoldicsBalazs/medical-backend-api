package ro.blz.medical.dtos;

import ro.blz.medical.domain.AppointmentSatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDTO(
         long id,
         long doctorId,
         long patientId,
         AppointmentSatus status,
         LocalDate date,
         LocalTime time
) {

}
