package ro.blz.medical.dtos;

import java.sql.Date;
import java.sql.Time;

public record AppointmentDetailsDTO (
        long id,
        String doctorName,
        String doctorLastName,
        String department,
        Date appointmentDate,
        Time appointmentTime,
        String status){

}
