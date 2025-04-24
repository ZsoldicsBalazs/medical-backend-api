package ro.blz.medical.exceptions;

import org.postgresql.util.PSQLException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse duplicateUserException(UserAlreadyExistsException ex) {
            return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFound(UserNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse authenticationException(AuthenticationException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
    }

    @ExceptionHandler(value = DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse doctorNotFound(DoctorNotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse nullPointerException(NullPointerException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse patientNotFound(PatientNotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse entityNotFound(EntityNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseList methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .toList();
        System.out.println(errors);
        return new ErrorResponseList(HttpStatus.BAD_REQUEST.value(),errors);
    }
    @ExceptionHandler(value = AppointmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse appointmentNotFound(AppointmentNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = ProcedureNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse procedureNotFound(ProcedureNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value= IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse illegalArgument(IllegalArgumentException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        // Extract the root cause
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof PSQLException) {
            org.postgresql.util.PSQLException psqlEx = (PSQLException) rootCause;
            String sqlState = psqlEx.getSQLState();
            String message = psqlEx.getMessage();

            // Handle unique constraint violation (e.g., duplicate key)
            if ("23505".equals(sqlState)) { // 23505 = unique_violation in PostgreSQL
                String constraintName = extractConstraintName(message);
                switch (constraintName) {
                    case "ukc0m60d5s6vi2scdd6puro62cn": // Replace with your actual constraint name
                        errors.put("error", "A patient with this CNP already exists.");
                        break;
                    default:
                        errors.put("error", "Duplicate entry violates database constraint");
                }
            } else {
                errors.put("error", "Database error: " + message);
            }
        } else {
            errors.put("error", "Database error: " + ex.getMessage());
        }

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),errors.toString());
    }

    private String extractConstraintName(String message) {
        // Example message: "ERROR: duplicate key value violates unique constraint "ukc0m60d5s6vi2scdd6puro62cn""
        if (message.contains("constraint \"")) {
            return message.split("constraint \"")[1].split("\"")[0];
        }
        return "unknown_constraint";
    }


}

