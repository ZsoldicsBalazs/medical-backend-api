package ro.blz.medical.dtos;

public record DoctorDTO(
        String username,
        String firstName,
        String lastName,
        String phone,
        String email,
        String department
) {
}
