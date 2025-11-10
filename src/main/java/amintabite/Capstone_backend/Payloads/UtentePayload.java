package amintabite.Capstone_backend.Payloads;

import amintabite.Capstone_backend.Enums.Roles;
import jakarta.validation.constraints.*;

public record UtentePayload(
        @NotBlank(message = "L'username non puo' essere vuoto")
        @Size(min = 3, max = 20, message = "l'username deve avere almeno 3 caratteri, massimo 20")
        String username,
        @NotBlank(message = "L'email non puo' essere vuota!")
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
        String email,
        @NotBlank(message = "La password non puo' essere vuota!")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "La password deve contenere una maiuscola, una minuscola ecc ecc ...")
        String password,
        @NotNull
        Roles role

) {
}
