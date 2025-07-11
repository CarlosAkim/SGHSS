package com.ProjetoBackend.sghss.model;

import com.ProjetoBackend.sghss.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Column(unique = true, length = 11)
    private String cpf;

    private LocalDate dataNascimento;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;

    private String senhaHash;

    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoUsuario usuario;

}
