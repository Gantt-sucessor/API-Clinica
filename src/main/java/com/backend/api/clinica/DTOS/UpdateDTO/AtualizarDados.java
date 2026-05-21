package com.backend.api.clinica.DTOS.UpdateDTO;

import jakarta.validation.constraints.Email;

public record AtualizarDados(
        String nomeCompleto,

        @Email
        String email
) {
}
