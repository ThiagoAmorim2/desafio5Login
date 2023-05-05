package com.microservice.login.domain.acesso;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.login.dto.AcessoDTO;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.services.AcessoSenhaServiceBase;
import com.microservice.login.utils.exception.SenhaIncorretaException;
import com.microservice.login.utils.exception.UsuarioNaoExisteException;


@Service
public class AcessoSenhaServiceImpl implements AcessoSenhaServiceBase {
    private final AcessoRepository acessoRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AcessoSenhaServiceImpl(AcessoRepository acessoRepository){
        this.acessoRepository = acessoRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public AcessoDAO buscarPorNome(String usuario){
        return acessoRepository.findByUsuario(usuario);
    }
    public String validarSenha(AcessoDTO acessoParaValidarLogin){
        AcessoDAO usuarioDoBancoDados = buscarPorNome(acessoParaValidarLogin.getUsuario());

        if(usuarioDoBancoDados == null){
            throw new UsuarioNaoExisteException("Usuário " + acessoParaValidarLogin.getUsuario() + " não existe");
        }

        var usuarioDoBanco = usuarioDoBancoDados.getUsuario();

        var senhaDoBanco = usuarioDoBancoDados.getSenha();

            if(usuarioDoBanco.equals(acessoParaValidarLogin.getUsuario())) {
                if (BCrypt.checkpw(acessoParaValidarLogin.getSenha(), senhaDoBanco))
                    return "Usuário Logado";
            }
            throw new SenhaIncorretaException("Senha informada está incorreta");
    }
}
