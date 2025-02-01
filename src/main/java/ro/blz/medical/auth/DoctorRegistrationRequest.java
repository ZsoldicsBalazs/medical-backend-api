package ro.blz.medical.auth;

public record DoctorRegistrationRequest(

        String username,
        String password,
        String firstName,
        String lastName,
        String phone,
        String email,
        String department,
        String salary
) {
}
