package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Paciente;
import com.ProjetoBackend.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscaId(@PathVariable Long id){
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente criar(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{id}")
    public Object atualizar(@PathVariable Long id, @RequestBody Paciente novoPaciente){
        Paciente atualizado = novoPaciente;
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNome(atualizado.getNome());
                    paciente.setEndereco(atualizado.getEndereco());
                    paciente.setCpf(atualizado.getCpf());
                    paciente.setAtivo(atualizado.isAtivo());
                    paciente.setEmail(atualizado.getEmail());
                    paciente.setSolicitacaoConsulta(atualizado.getSolicitacaoConsulta());
                    paciente.setDataNascimento(atualizado.getDataNascimento());
                    paciente.setTelefone(atualizado.getTelefone());
                    paciente.setSenhaHash(atualizado.getSenhaHash());
                    paciente.setUsuario(atualizado.getUsuario());
                    return ResponseEntity.ok(pacienteRepository.save(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
