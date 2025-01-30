package ro.blz.medical.domain;

public record UserRegistrationRequest(
        String CNP,
        String username,
        String password,
        String firstName,
        String lastName,
        String phone,
        String email

) {
}
