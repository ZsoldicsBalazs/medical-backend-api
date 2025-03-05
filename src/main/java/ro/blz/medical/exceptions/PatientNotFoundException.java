package ro.blz.medical.exceptions;

public class PatientNotFoundException extends BaseException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
