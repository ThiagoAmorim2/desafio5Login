package com.microservice.login.controller;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.services.AcessoSenhaServiceBase;
import com.microservice.login.services.AcessoServiceBase;
import com.microservice.login.dto.AcessoDTO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.AcessoResponseDTO;
import com.microservice.login.dto.ListaGenericaResponseDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;
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
    public ResponseEntity<ListaGenericaResponseDTO<AcessoResponseDTO>> verUsuarios(
                                    @PathVariable Long id, 
                                    @RequestParam PaginacaoRequestDTO paginacao,
                                    @RequestParam AcessoFiltroUsuarioDTO filtro) throws UsuarioNaoAdminException {
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados(id, paginacao, filtro));
    }

    @PostMapping(value = "/post")
    public ResponseEntity<AcessoDTO> adicionarAcesso(@RequestBody @Valid AcessoDTO novoAcessoDto) {
        return ResponseEntity.ok(acessoServiceImpl.adicionarNovoAcesso(novoAcessoDto));
    }


    //Ajuste na validação de senha - envio da senha sem criptografia
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<AcessoDTO> atualizarAcesso(@PathVariable Long id, @RequestBody AcessoDTO acessoParaAtualizarDto) throws UsuarioNaoAdminException {
        var atualizar = acessoServiceImpl.atualizarAcesso(id, acessoParaAtualizarDto);
        return ResponseEntity.ok(atualizar);
    }


    //Httpstatus revisar retorno errado - Verificar Consistência de dados - pesquisar com ||
    @PostMapping(value = "/entrar")
    public ResponseEntity<String> logarOk(@RequestBody AcessoDTO acessoDto){
        return ResponseEntity.ok().body(senhaService.validarSenha(acessoDto));
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletarAcesso(@PathVariable Long id, @RequestBody AcessoDTO acessoDto) throws UsuarioNaoAdminException {
        return ResponseEntity.ok().body(acessoServiceImpl.deletarAcesso(id, acessoDto));
    }

}
