package com.microservice.login.domain.acesso;

import com.microservice.login.dto.AcessoDto;
import com.microservice.login.repository.AcessoRepository;
import com.microservice.login.services.AcessoSenhaServiceBase;
import com.microservice.login.utils.exception.UsuarioNaoExisteException;
import com.microservice.login.utils.mappers.AcessoMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AcessoSenhaServiceImpl implements AcessoSenhaServiceBase {
    private final AcessoRepository acessoRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AcessoSenhaServiceImpl(AcessoRepository acessoRepository){
        this.acessoRepository = acessoRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Acesso buscarPorNome(String nomeUsuario){
        return acessoRepository.findByNomeUsuario(nomeUsuario);
    }
    public String validarSenha(AcessoDto acessoParaValidarLogin){
        Acesso usuarioDoBancoDados = buscarPorNome(acessoParaValidarLogin.getUsuario());

        var usuarioDoBanco = usuarioDoBancoDados.getNomeUsuario();
        if(usuarioDoBanco == null){
            throw new IllegalArgumentException("Usuário não existe");
        }
        var senhaDoBanco = usuarioDoBancoDados.getSenha();

        try{
            if(usuarioDoBanco.equals(acessoParaValidarLogin.getUsuario())){
                if(BCrypt.checkpw(acessoParaValidarLogin.getSenha(), senhaDoBanco))
                    return "Usuário Logado";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "Usuário ou senha inválidos";

    }
}
