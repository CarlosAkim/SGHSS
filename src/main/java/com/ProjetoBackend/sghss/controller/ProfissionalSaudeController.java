package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.ProfissionalSaude;
import com.ProjetoBackend.sghss.repository.ProfissionalSaudeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalSaudeController {
    @Autowired
    private ProfissionalSaudeRepository repo;

    @GetMapping
    public List<ProfissionalSaude> listarTodos(){
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> buscarId(@PathVariable Long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<ProfissionalSaude> criar(@Valid @RequestBody ProfissionalSaude profissional){
        return ResponseEntity.ok(repo.save(profissional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaude> atualizar(@PathVariable Long id, @Valid @RequestBody ProfissionalSaude novo){
        return repo.findById(id)
                .map(existente ->{
                    existente.setCpf(novo.getCpf());
                    existente.setEmail(novo.getEmail());
                    existente.setTelefone(novo.getTelefone());
                    existente.setNome(novo.getNome());
                    existente.setDataNascimento(novo.getDataNascimento());
                    existente.setAtivo(novo.isAtivo());
                    existente.setSenhaHash(novo.getSenhaHash());
                    existente.setEspecialidade(novo.getEspecialidade());
                    existente.setRegistroConselho(novo.getRegistroConselho());
                    existente.setDataContratacao(novo.getDataContratacao());
                    return ResponseEntity.ok(repo.save(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();

    }

}
