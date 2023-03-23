package com.microservice.login.controller;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.services.AcessoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class AcessoController {

    private AcessoServiceImpl acessoServiceImpl;

    public AcessoController(AcessoServiceImpl acessoServiceImpl) {
        this.acessoServiceImpl = acessoServiceImpl;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Acesso>> verUsuarios(){
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados());
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Acesso> adicionarAcesso(@RequestBody AcessoDto novoAcessoDto){
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }


    @GetMapping(value = "/getbynome/{id}")
    public ResponseEntity<Acesso> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(acessoServiceImpl.buscarAcessoPorId(id));
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Acesso> atualizarAcesso(@PathVariable UUID id, @RequestBody Acesso acesso) {
        return ResponseEntity.ok(acessoServiceImpl.atualizarAcesso(id, acesso));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletarAcesso(@PathVariable UUID id){
        acessoServiceImpl.deletarAcesso(id);
    }
}
