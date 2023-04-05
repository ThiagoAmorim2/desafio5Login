package com.microservice.login.controller;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.services.AcessoSenhaServiceBase;
import com.microservice.login.services.AcessoServiceBase;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.domain.acesso.AcessoServiceImpl;
import com.microservice.login.domain.acesso.AcessoSenhaServiceImpl;
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

    private AcessoServiceBase acessoServiceImpl;

    private AcessoSenhaServiceBase senhaService;

    public AcessoController(AcessoServiceBase acessoServiceImpl, AcessoSenhaServiceBase senhaService) {
        this.acessoServiceImpl = acessoServiceImpl;
        this.senhaService = senhaService;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<List<Acesso>> verUsuarios(@PathVariable UUID id) throws UsuarioNaoAdminException {
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados(id));
    }

    @PostMapping(value = "/post")
    public ResponseEntity<AcessoDto> adicionarAcesso(@RequestBody @Valid AcessoDto novoAcessoDto) {
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }


    //Ajuste na validação de senha - envio da senha sem criptografia
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<AcessoDto> atualizarAcesso(@PathVariable UUID id, @RequestBody AcessoDto acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        var atualizar = acessoServiceImpl.atualizarAcesso(id, acessoParaAtualizarDto);
        return ResponseEntity.ok(atualizar);
    }


    //Httpstatus revisar retorno errado - Verificar Consistência de dados - pesquisar com ||
    @PostMapping(value = "/entrar")
    public ResponseEntity<String> logarOk(@RequestBody AcessoDto acessoDto){
        return ResponseEntity.ok().body(senhaService.validarSenha(acessoDto));
    }


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
