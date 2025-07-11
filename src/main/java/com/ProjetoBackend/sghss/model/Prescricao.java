package com.ProjetoBackend.sghss.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;



@Data
@Entity
@Table(name = "Prescicoes")
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String medicamento;
    private String dosagem;
    private String instrucoes;
    private LocalDate dataEmissao;

    @ManyToOne
    @JoinColumn(name = "profissional_id",  nullable = false)
    private ProfissionalSaude profissionalSaude;


}
