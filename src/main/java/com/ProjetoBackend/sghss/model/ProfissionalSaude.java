package com.ProjetoBackend.sghss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Profissional_saude")
public class ProfissionalSaude extends Pessoa{
    private String especialidade;

    @Column(name = "Registro_Conselho", unique = true)
    private String registroConselho;

    private LocalDate dataContratacao;

}
