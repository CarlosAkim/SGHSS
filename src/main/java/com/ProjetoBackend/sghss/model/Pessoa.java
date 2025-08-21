package com.ProjetoBackend.sghss.model;

import com.ProjetoBackend.sghss.enums.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "O CPF é obrigatorio")
    @Size(min = 11,max=11, message = "CPF deve ter 11 digitos")
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
