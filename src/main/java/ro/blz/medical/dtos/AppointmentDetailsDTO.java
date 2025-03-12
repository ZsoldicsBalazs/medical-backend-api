package ro.blz.medical.dtos;

import ro.blz.medical.domain.AppointmentSatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDetailsDTO (
        long id,
        String doctorName,
        String doctorLastName,
        String department,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        AppointmentSatus status){

}
