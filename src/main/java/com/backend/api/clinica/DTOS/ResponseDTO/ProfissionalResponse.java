package com.backend.api.clinica.DTOS.ResponseDTO;

import java.util.UUID;

public record ProfissionalResponse(UUID publicId, String nomeCompleto, String email) {
}
