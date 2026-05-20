package com.backend.api.clinica.Repository;

import com.backend.api.clinica.Entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
