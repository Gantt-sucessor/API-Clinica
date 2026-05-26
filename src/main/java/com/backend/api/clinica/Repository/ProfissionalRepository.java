package com.backend.api.clinica.Repository;

import com.backend.api.clinica.Entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    Optional<Profissional> findByEmail(String email);
}
