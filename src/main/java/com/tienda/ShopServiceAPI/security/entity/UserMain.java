package com.tienda.ShopServiceAPI.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserMain implements UserDetails {

    private String name;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; //clase de seguridad, cambia clase rol por autoridades, autenticacion y la autorizacion

    //clase que convierte al usuario de la bd a un usuario main con sus autoridades, que son los roles convertidos en listado de granted autorities
    public UserMain(String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //convierte usuario de la bd en un usuario main que usamos para darle sus privilegios
    public static UserMain build(User user){
        //convertimos la clase rol en clase grantedAuthoritie
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(
                        rol.getRol_name().name())).collect(Collectors.toList());
        return new UserMain(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
