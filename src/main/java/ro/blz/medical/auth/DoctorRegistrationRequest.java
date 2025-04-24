package ro.blz.medical.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegistrationRequest(

        @NotBlank(message = "Username name cannot be empty")
        String username,
        @NotBlank(message = "Password cannot be empty")
        String password,
        @NotBlank(message = "First name cannot be empty")
        String firstName,
        @NotBlank(message = "Last name cannot be empty")
        String lastName,
        @NotBlank(message = "Phone number cannot be empty")
        @Pattern(
                regexp = "^\\+?(\\d{1,3})?[ ]?\\d{6,14}$",
                message = "Invalid phone number. Use international format, e.g., +40738928332"
        )
        String phone,
        @NotBlank(message = "Email can not be empty")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Department can not be empty")
        String department,
        @NotNull(message = "Salary must not be null")
        String salary
) {
}
