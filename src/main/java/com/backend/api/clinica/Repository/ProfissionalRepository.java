package com.backend.api.clinica.Repository;

import com.backend.api.clinica.Entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    Optional<Profissional> findByEmail(String email);

    // Metodo customizado para buscar pelo UUID público
    Optional<Profissional> findByPublicId(UUID publicId);

    // Opcional: Verificar exitência
    boolean existsByPublicId(UUID publicId);
}
