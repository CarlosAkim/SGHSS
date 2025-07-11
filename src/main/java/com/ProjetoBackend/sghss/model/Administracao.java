package com.ProjetoBackend.sghss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Adminiistracao")
public class Administracao extends Pessoa{
    private String dataContratacao;

}
