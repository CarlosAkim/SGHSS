package com.ProjetoBackend.sghss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Pacientes")
public class Paciente extends Pessoa {
    private String endereco;
    private LocalDate SolicitacaoConsulta;


}
