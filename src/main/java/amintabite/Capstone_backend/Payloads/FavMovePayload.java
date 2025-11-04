package amintabite.Capstone_backend.Payloads;

import jakarta.validation.constraints.NotBlank;

public record FavMovePayload(
        @NotBlank
        String move_input,

        @NotBlank
        String character_name
) {}

