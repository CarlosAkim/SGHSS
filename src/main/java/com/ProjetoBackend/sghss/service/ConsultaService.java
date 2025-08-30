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

import java.util.List;

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
        c.setStatus(req.getStatus());
        c.setPaciente(paciente);
        c.setProfissional(prof);

        Consulta salvo = consultaRepo.save(c);
        return toReponse(salvo);
    }
    public ConsultaResponse buscarPorId(long Id){
        Consulta c = consultaRepo.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Consulta não encontrada" + Id));
        return toReponse(c);
    }
    public List<Consulta> listarTodos(){
        return consultaRepo.findAll();
    }
    public ConsultaResponse atualizar(long id, ConsultaRequest req){
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Não Consulta não foi encontrada " + id));
        if (req.getPacienteId() != null){
            Paciente p = pacienteRepo.findById(req.getPacienteId())
                .orElseThrow(()-> new ResourceNotFoundException("Paciente não encontrado" + req.getPacienteId()));
            c.setPaciente(p);
        }

        if(req.getPacienteId() != null){
            ProfissionalSaude prof = profissinalRepo.findById(req.getProfissionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado" + req.getProfissionalId()));
            c.setProfissional(prof);
        }
        if (req.getDataHora() != null){ c.setDataHora(req.getDataHora());}
        if(req.getStatus() != null) c.setStatus(req.getStatus());

        return toReponse(consultaRepo.save(c));
}
    public void deletar(long id){
        if(!consultaRepo.existsById(id)){
            throw new ResourceNotFoundException("Consulta não encontrada" + id);
        }
        consultaRepo.deleteById(id);
    }
    public ConsultaResponse toReponse(Consulta c){
        ConsultaResponse resp = new ConsultaResponse();
        resp.setId(c.getId());
        resp.setDataHora(c.getDataHora());
        resp.setStatus(c.getStatus());
        resp.setPacienteId(c.getPaciente().getId());
        resp.setPacienteNome(c.getPaciente().getNome());
        resp.setPacienteId(c.getPaciente().getId());
        return resp;
    }
}