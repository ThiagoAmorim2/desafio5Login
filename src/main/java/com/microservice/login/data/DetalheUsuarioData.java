//package com.microservice.login.data;
//
//import com.microservice.login.domain.acesso.Acesso;
//import com.microservice.login.dto.AcessoDto;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//
//public class DetalheUsuarioData implements UserDetails {
//
//    private final Optional<AcessoDto> acessoDtoOptional;
//
//    public DetalheUsuarioData(Optional<AcessoDto> acessoDtoOptional) {
//        this.acessoDtoOptional = acessoDtoOptional;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public String getPassword() {
//        return acessoDtoOptional.orElse(new AcessoDto()).getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return acessoDtoOptional.orElse(new AcessoDto()).getUsuario();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
