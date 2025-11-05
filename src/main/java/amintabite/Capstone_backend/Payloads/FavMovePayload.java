package amintabite.Capstone_backend.Payloads;

import jakarta.validation.constraints.NotBlank;

public record FavMovePayload(
        @NotBlank String moveInput,


        @NotBlank String characterName
) {}