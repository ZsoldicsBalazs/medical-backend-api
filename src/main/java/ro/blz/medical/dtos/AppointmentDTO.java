package ro.blz.medical.dtos;

import ro.blz.medical.domain.AppointmentSatus;

import java.sql.Date;
import java.sql.Time;
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

    public static AppointmentDTO fromRaw(Object[] raw){
        long id = ((Number) raw[0]).longValue();
        long doctorId = ((Number) raw[1]).longValue();
        long patientId = ((Number) raw[2]).longValue();
        AppointmentSatus status = AppointmentSatus.valueOf((String) raw[3]);
        LocalDate date = ((Date)raw[4]).toLocalDate();
        LocalTime time = ((Time)raw[5]).toLocalTime();
        return new AppointmentDTO(id,doctorId,patientId,status,date,time);
    }
}
