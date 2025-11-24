package amintabite.Capstone_backend.Payloads;

import amintabite.Capstone_backend.Enums.Roles;
import jakarta.validation.constraints.*;

public record UtentePayload(
        @NotBlank(message = "Username can't be empty")
        @Size(min = 3, max = 20, message = "Username must have 3 letters, max 20")
        String username,
        @NotBlank(message = "Email can't be empty!")
        @Email(message = "Email Address must be correct")
        String email,
        @NotBlank(message = "Password can't be empty")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "Password must have 1 capital letter, 1 number and 1 special character")
        String password,
        @NotNull(message = "You must have a role")
        Roles role

) {
}
