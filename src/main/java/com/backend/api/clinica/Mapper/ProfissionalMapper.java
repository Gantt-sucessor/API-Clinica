package com.backend.api.clinica.Mapper;

import com.backend.api.clinica.DTOS.RequestDTO.ProfissionalRequest;
import com.backend.api.clinica.DTOS.ResponseDTO.ProfissionalResponse;
import com.backend.api.clinica.DTOS.UpdateDTO.AtualizarDados;
import com.backend.api.clinica.Entity.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    Profissional toEntity(ProfissionalRequest dto);

    ProfissionalResponse toResponse(Profissional profissional);

    void updateEntityFromDto(AtualizarDados dto, @MappingTarget Profissional entity);

}
