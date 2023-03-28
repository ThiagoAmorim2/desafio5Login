package com.microservice.login.controller;

import com.microservice.login.domain.acesso.Acesso;
import com.microservice.login.dto.AcessoDto;
import com.microservice.login.services.AcessoServiceImpl;
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

    @GetMapping(value = "/get")
    public ResponseEntity<List<Acesso>> verUsuarios(){
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados());
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Acesso> adicionarAcesso(@RequestBody @Valid AcessoDto novoAcessoDto){
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }


    @GetMapping(value = "/getbynome/{id}")
    public ResponseEntity<Acesso> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(acessoServiceImpl.buscarAcessoPorId(id));
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Acesso> atualizarAcesso(@RequestBody @Valid AcessoDto atualizarAcessoDto, @PathVariable UUID id) {
        return ResponseEntity.ok(acessoServiceImpl.atualizarAcesso(id, atualizarAcessoDto));
    }

//    @PostMapping(value = "/entrar")
//    public ResponseEntity<UserDetails> logarOk(@RequestBody AcessoDto acessoDto){
//        return ResponseEntity.ok().body(detalheUsuarioServiceImpl.loadUserByUsername(acessoDto.getUsuario()));
//    }

    @DeleteMapping(value = "/delete/{id}")
    public void deletarAcesso(@PathVariable UUID id){
        acessoServiceImpl.deletarAcesso(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidacaoException(MethodArgumentNotValidException ex){
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((erro) ->{
                    String nomeAtributo = ((FieldError) erro).getField();
                    String messagemErro = erro.getDefaultMessage();

                    erros.put(nomeAtributo, messagemErro);
                }
        );
        return erros;
    }
}
