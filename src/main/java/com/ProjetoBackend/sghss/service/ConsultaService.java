package com.ProjetoBackend.sghss.service;

import com.ProjetoBackend.sghss.dto.consulta.ConsultaRequest;
import com.ProjetoBackend.sghss.dto.consulta.ConsultaResponse;
import com.ProjetoBackend.sghss.exception.ResourceNotFoundException;
import com.ProjetoBackend.sghss.model.Consulta;
import com.ProjetoBackend.sghss.model.Paciente;
import com.ProjetoBackend.sghss.model.ProfissionalSaude;
import com.ProjetoBackend.sghss.repository.ConsultaRepository;
import com.ProjetoBackend.sghss.repository.PacienteRepository;
import com.ProjetoBackend.sghss.repository.ProfissionalSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired private ConsultaRepository consultaRepo;
    @Autowired private PacienteRepository pacienteRepo;
    @Autowired private ProfissionalSaudeRepository profissinalRepo;

    public ConsultaResponse criar (ConsultaRequest req){
        Paciente paciente = pacienteRepo.findById(req.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + req.getPacienteId()));
        ProfissionalSaude prof = profissinalRepo.findById(req.getProfissionalId())
                .orElseThrow(()-> new ResourceNotFoundException("Profissinal não encontrado: " + req.getPacienteId()));
        Consulta c = new Consulta();
        c.setDataHora(req.getDataHora());
        c.setStatus(req.getConsulta());
        c.setPaciente(paciente);
        c.setProfissional(prof);

        Consulta salvo = consultaRepo.save(c);
        return toReponse(salvo);
    }

    public ConsultaResponse toReponse(Consulta c){
        ConsultaResponse resp = new ConsultaResponse();
        resp.setId(c.getId());
        resp.setDataHora(c.getDataHora());
        resp.setStatus(c.getStatus());
        resp.setPacienteId(c.getPaciente().getId());
    }
}
