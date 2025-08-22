package com.ProjetoBackend.sghss.dto.consulta;

import com.ProjetoBackend.sghss.enums.StatusConsulta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaResponse {
    private Long id;
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private Long pacienteId;
    private String pacienteNome;
    private Long profissionalId;
    private String profissionalNome;
}
