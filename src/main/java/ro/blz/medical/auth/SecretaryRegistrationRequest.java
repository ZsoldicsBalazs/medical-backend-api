package ro.blz.medical.auth;

public record SecretaryRegistrationRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Double salary
) {
}
