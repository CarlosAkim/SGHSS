package com.ProjetoBackend.sghss.repository;

import com.ProjetoBackend.sghss.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
