package amintabite.Capstone_backend.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginPayload(
        @NotBlank(message = "L'email non puo' essere vuota!")
        @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
String email,
@NotBlank(message = "La password non puo' essere vuota!")
@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$", message = "La password deve contenere una maiuscola, una minuscola ecc ecc ...")
String password

) {
}
