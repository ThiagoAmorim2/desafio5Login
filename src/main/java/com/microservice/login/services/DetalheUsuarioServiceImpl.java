//package com.microservice.login.services;
//
//import com.microservice.login.data.DetalheUsuarioData;
//import com.microservice.login.dto.AcessoDto;
//import com.microservice.login.repository.AcessoRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class DetalheUsuarioServiceImpl implements UserDetailsService {
//
//    private final AcessoRepository acessoRepository;
//
//    public DetalheUsuarioServiceImpl(AcessoRepository acessoRepository) {
//        this.acessoRepository = acessoRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<AcessoDto> acessoDtoOptional = acessoRepository.findByUsuario(username);
//        if (acessoDtoOptional.isEmpty()){
//            throw new UsernameNotFoundException("Usuário "+ username + "não existe");
//        }
//
//        return new DetalheUsuarioData(acessoDtoOptional);
//    }
//}
