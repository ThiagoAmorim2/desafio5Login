package com.microservice.login.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.login.dto.AcessoDTO;
import com.microservice.login.dto.AcessoFiltroUsuarioDTO;
import com.microservice.login.dto.AcessoResponseDTO;
import com.microservice.login.dto.ListaGenericaResponseDTO;
import com.microservice.login.dto.PaginacaoRequestDTO;
import com.microservice.login.services.AcessoSenhaServiceBase;
import com.microservice.login.services.AcessoServiceBase;
import com.microservice.login.utils.exception.UsuarioNaoAdminException;

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
                                    @RequestParam(value = "usuario") String usuario)
                                    // @RequestParam(value = "sort") String sort) throws UsuarioNaoAdminException {
                                        throws UsuarioNaoAdminException {
        AcessoFiltroUsuarioDTO filtroUsuarioDTO = new AcessoFiltroUsuarioDTO(usuario);
        PaginacaoRequestDTO paginacao = new PaginacaoRequestDTO();
        // paginacao.setSort(sort);
        return ResponseEntity.ok().body(acessoServiceImpl.verUsuariosCadastrados(id, paginacao, filtroUsuarioDTO));
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
