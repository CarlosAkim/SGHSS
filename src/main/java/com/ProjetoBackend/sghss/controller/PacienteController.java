package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Paciente;
import com.ProjetoBackend.sghss.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Paciente> criar(@Valid @RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteRepository.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente novoPaciente) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNome(novoPaciente.getNome());
                    paciente.setEndereco(novoPaciente.getEndereco());
                    paciente.setCpf(novoPaciente.getCpf());
                    paciente.setAtivo(novoPaciente.isAtivo());
                    paciente.setEmail(novoPaciente.getEmail());
                    paciente.setSolicitacaoConsulta(novoPaciente.getSolicitacaoConsulta());
                    paciente.setDataNascimento(novoPaciente.getDataNascimento());
                    paciente.setTelefone(novoPaciente.getTelefone());
                    paciente.setSenhaHash(novoPaciente.getSenhaHash());
                    paciente.setUsuario(novoPaciente.getUsuario());
                    return ResponseEntity.ok(pacienteRepository.save(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(pacienteRepository.existsById(id)){
            pacienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
