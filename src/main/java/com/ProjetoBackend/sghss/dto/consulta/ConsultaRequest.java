package com.ProjetoBackend.sghss.dto.consulta;

import com.ProjetoBackend.sghss.enums.StatusConsulta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaRequest {
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private Long pacienteId;
    private Long profissionalId;
}
