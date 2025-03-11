package ro.blz.medical.exceptions;

public class AppointmentNotFoundException extends BaseException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
