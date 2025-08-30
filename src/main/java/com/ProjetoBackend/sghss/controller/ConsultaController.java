package com.ProjetoBackend.sghss.controller;

import com.ProjetoBackend.sghss.dto.consulta.ConsultaRequest;
import com.ProjetoBackend.sghss.dto.consulta.ConsultaResponse;
import com.ProjetoBackend.sghss.model.Consulta;
import com.ProjetoBackend.sghss.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<Consulta> listarTodos(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarPorId (@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> criar(@Valid @RequestBody ConsultaRequest req){
        return ResponseEntity.ok(service.criar(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponse> atualizar(@PathVariable long id, @Valid @RequestBody ConsultaRequest req){
        return ResponseEntity.ok(service.atualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultaResponse> deletar(@PathVariable long id ){
         service.deletar(id);
         return ResponseEntity.noContent().build();
    }
}
