package ro.blz.medical.dtos;

public record DoctorDTO(
        long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String department
) implements BaseDTO {
}
