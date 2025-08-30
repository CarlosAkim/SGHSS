package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Internacao;
import com.ProjetoBackend.sghss.repository.InternacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    @Autowired
    private InternacaoRepository repo;

    @GetMapping
    public List<Internacao> listar(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internacao> buscarPorId(@PathVariable long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Internacao criar(@Valid @RequestBody Internacao internacao){
        return repo.save(internacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internacao> atualizar(@PathVariable long id, @Valid @RequestBody Internacao nova){
        return repo.findById(id)
                .map(existente ->{
                    existente.setId(nova.getId());
                    existente.setMotivo(nova.getMotivo());
                    existente.setLeito(nova.getLeito());
                    existente.setDataSaida(nova.getDataSaida());
                    existente.setPaciente(nova.getPaciente());
                    existente.setDataEntrada(nova.getDataEntrada());
                    return ResponseEntity.ok(repo.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
         return  ResponseEntity.notFound().build();
        }
}
