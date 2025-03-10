package ro.blz.medical.dtos;

import java.sql.Date;
import java.sql.Time;

public record AppointmentDetailsDTO (
        long id,
        String doctorName,
        String doctorLastName,
        String department,
        Date appointment_date,
        Time appointment_time){

//    public AppointmentDetailsDTO(long id, String doctorName, String doctorLastName, String department, String appointmentDate, String appointmentTime) {
//        this(id,doctorName,doctorLastName,department,appointmentDate,appointmentTime);
//    }
}
