package amintabite.Capstone_backend.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record FavMovePayload(
        @NotBlank String moveInput,


        @NotBlank String characterName,


        @NotBlank     String damage,

        @NotBlank   String startup,


        @NotBlank   String onBlock,




        @NotBlank     String onHit,




        @NotBlank       String hitLevel,




        @NotBlank      String recovery



) {}