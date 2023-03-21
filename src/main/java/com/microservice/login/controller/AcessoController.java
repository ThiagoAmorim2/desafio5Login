package com.microservice.login.controller;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.services.AcessoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class AcessoController {

    private AcessoServiceImpl acessoServiceImpl;


    @GetMapping("/get")
    public ResponseEntity<List<Acesso>> verUsuarios(){
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados());
    }

    @PostMapping("/post")
    public ResponseEntity<Acesso> adicionarAcesso(@RequestBody AcessoDto novoAcessoDto){
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }
}
