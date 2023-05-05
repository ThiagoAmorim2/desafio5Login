package com.microservice.login.utils.mappers;

import com.microservice.login.domain.acesso.AcessoDAO;
import com.microservice.login.dto.AcessoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Tuple;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;


@Component
public class AcessoMapper {
    public AcessoDTO converterAcessoEmAcessoDto(AcessoDAO acesso){
        AcessoDTO acessoDto = new AcessoDTO();
        acessoDto.setId(acesso.getId());
        acessoDto.setUsuario(acesso.getNomeUsuario());
        acessoDto.setSenha(acesso.getSenha());
        acessoDto.setFuncao(acesso.getFuncao());
        return acessoDto;
    }

    public AcessoDAO converterAcessoDtoEmAcesso(AcessoDTO acessoDto){
        AcessoDAO acesso = new AcessoDAO();
        acesso.setId(acessoDto.getId());
        acesso.setNomeUsuario(acessoDto.getUsuario());
        acesso.setSenha(acessoDto.getSenha());
        acesso.setFuncao(acessoDto.getFuncao());
        return acesso;
    }

    public static List<AcessoDAO> converterListaTuplaEmListaAcessoDAO(List<Tuple> tuplas){
        List<AcessoDAO> resultado = new ArrayList<>();
        for(Tuple tupla : tuplas){
            AcessoDAO acessoDAO = new AcessoDAO();
            acessoDAO.setId(toLong(tupla.get("id"), null));
            acessoDAO.setNomeUsuario(toString(tupla.get("usuario"), null));
            acessoDAO.setSenha(toString(tupla.get("senha"), null));
            acessoDAO.setFuncao(toString(tupla.get("funcao"), null));
            resultado.add(acessoDAO);
        }
        return resultado;
    }

    public static String toString(Object objeto, String valorDefault){
        if(valorDefault == null){
            valorDefault = "";
        }
        return Objects.toString(objeto, valorDefault);
    }

    /**
     * @param objeto
     * @param valorDefault
     * @return
     */
    public static Long toLong(Object objeto, Long valorDefault){
        if(objeto instanceof Boolean){
            valorDefault = 
                ((Boolean) objeto).booleanValue() ? NumberUtils.LONG_ONE : NumberUtils.LONG_ZERO;
        }else if(valorDefault == null){
            valorDefault = NumberUtils.LONG_ZERO;
        }

        return NumberUtils.toLong(Objects.toString(objeto, ""), valorDefault);
    }
}
