package com.microservice.login.controller;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.services.AcessoServiceImpl;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class AcessoController {

    private AcessoServiceImpl acessoServiceImpl;

//    private DetalheUsuarioServiceImpl detalheUsuarioServiceImpl;

    public AcessoController(AcessoServiceImpl acessoServiceImpl) {
        this.acessoServiceImpl = acessoServiceImpl;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<List<Acesso>> verUsuarios(@PathVariable UUID id) throws UsuarioNaoAdminException {
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados(id));
    }

    @PostMapping(value = "/post")
    public ResponseEntity<AcessoDto> adicionarAcesso(@RequestBody @Valid AcessoDto novoAcessoDto) {
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<AcessoDto> atualizarAcesso(@PathVariable UUID id, @RequestBody AcessoDto acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        var atualizar = acessoServiceImpl.atualizarAcesso(id, acessoParaAtualizarDto);
        return ResponseEntity.ok(atualizar);
    }


//    @PostMapping(value = "/entrar")
//    public ResponseEntity<UserDetails> logarOk(@RequestBody AcessoDto acessoDto){
//        return ResponseEntity.ok().body(detalheUsuarioServiceImpl.loadUserByUsername(acessoDto.getUsuario()));
//    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletarAcesso(@PathVariable UUID id, @RequestBody AcessoDto acessoDto) throws UsuarioNaoAdminException {
        return ResponseEntity.ok().body(acessoServiceImpl.deletarAcesso(id, acessoDto));
    }


    //ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidacaoException(MethodArgumentNotValidException ex){
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((erro) -> {
                    String nomeAtributo = ((FieldError) erro).getField();
                    String messagemErro = erro.getDefaultMessage();
                    erros.put(nomeAtributo, messagemErro);
                }
        );
        return erros;
    }
}
