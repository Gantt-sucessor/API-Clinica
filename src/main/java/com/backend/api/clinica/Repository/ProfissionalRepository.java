package com.backend.api.clinica.Repository;

import com.backend.api.clinica.Entity.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {
}
