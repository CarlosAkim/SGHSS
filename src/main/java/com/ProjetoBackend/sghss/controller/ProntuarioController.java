package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Prontuario;
import com.ProjetoBackend.sghss.repository.ProntuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioRepository repo;

    @GetMapping
    public List<Prontuario> listarTodos(){
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Prontuario criar(@Valid @RequestBody Prontuario req){
        return repo.save(req);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable long id, @Valid @RequestBody Prontuario novo){
        return repo.findById(id)
                .map(existente->{
                    existente.setId(novo.getId());
                    existente.setPaciente(novo.getPaciente());
                    existente.setObservacoes(novo.getObservacoes());
                    existente.setConsulta(novo.getConsulta());
                    existente.setProfissionalSaude(novo.profissionalSaude);
                    existente.setDataRegistro(novo.getDataRegistro());
                    return ResponseEntity.ok(repo.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
