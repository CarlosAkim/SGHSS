package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Prescricao;
import com.ProjetoBackend.sghss.repository.PrescricaoRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoRepository repo;

    @GetMapping
    public List<Prescricao> listarTodos(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescricao> buscarPorId(@PathVariable long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prescricao criar(@Valid @RequestBody Prescricao prescricao){
        return repo.save(prescricao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescricao> atualizar(@PathVariable long id, @Valid @PathVariable Prescricao nova){
        return repo.findById(id)
                .map(existente->{
                    existente.setId(nova.getId());
                    existente.setInstrucoes(nova.getInstrucoes());
                    existente.setDosagem(nova.getDosagem());
                    existente.setPaciente(nova.getPaciente());
                    existente.setMedicamento(nova.getMedicamento());
                    existente.setDataPrescicao(nova.getDataPrescicao());
                    existente.setProfissionalSaude(nova.getProfissionalSaude());
                    return ResponseEntity.ok(repo.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
