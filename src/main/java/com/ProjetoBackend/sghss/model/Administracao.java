package com.ProjetoBackend.sghss.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Administracao")
public class Administracao extends Pessoa{

    private String dataContratacao;

}
