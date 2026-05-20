package com.backend.api.clinica.DTOS.RequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfissionalRequest(

        @NotBlank
        @Schema(
                description = "Nome do profissional",
                example = "João James Antonio"
        )
        String nomeCompleto,

        @NotBlank
        @Email
        @Schema(
                description = "Email do profissional",
                example = "exemplo@gmail.com"
        )
        String email,

        @NotBlank
        @Size(min = 6)
        @Schema(
                description = "Senha do profissional",
                example = "123456"
        )
        String senha

) {
}
