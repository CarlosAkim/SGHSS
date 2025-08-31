package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.model.Administracao;
import com.ProjetoBackend.sghss.repository.AdministracaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administracao")
public class AdministracaoController {
    @Autowired
    private AdministracaoRepository repo;

    @GetMapping
    public List<Administracao> listarTodos(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administracao> buscarPorId(@PathVariable long id){
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Administracao cria(@Valid @RequestBody Administracao adm){
        return repo.save(adm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administracao> atualizar(@PathVariable long id, Administracao novo){
        return repo.findById(id)
                .map(existente ->{
                    existente.setId(novo.getId());
                    existente.setNome(novo.getCpf());
                    existente.setDataContratacao(novo.getDataContratacao());
                    existente.setCpf(novo.getCpf());
                    existente.setAtivo(novo.isAtivo());
                    existente.setEmail(novo.getEmail());
                    existente.setDataNascimento(novo.getDataNascimento());
                    existente.setSenhaHash(novo.getSenhaHash());
                    existente.setTelefone(novo.getTelefone());
                    existente.setUsuario(novo.getUsuario());
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
