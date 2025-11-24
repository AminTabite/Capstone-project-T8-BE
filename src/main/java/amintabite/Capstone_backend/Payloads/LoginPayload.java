package amintabite.Capstone_backend.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginPayload(
        @NotBlank(message = "Email can't be empty")
        @Email(message = "Email adress must be correct")
String email,
@NotBlank(message = "Password can't be empty")
@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "Password must have 1 capital letter, 1 number and 1 special character")
String password

) {
}
