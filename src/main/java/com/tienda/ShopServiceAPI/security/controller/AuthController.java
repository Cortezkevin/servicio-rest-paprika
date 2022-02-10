package com.tienda.ShopServiceAPI.security.controller;

import com.tienda.ShopServiceAPI.security.dto.JwtDto;
import com.tienda.ShopServiceAPI.security.dto.LoginUser;
import com.tienda.ShopServiceAPI.security.dto.NewUser;
import com.tienda.ShopServiceAPI.security.entity.Rol;
import com.tienda.ShopServiceAPI.security.entity.User;
import com.tienda.ShopServiceAPI.security.enums.RolName;
import com.tienda.ShopServiceAPI.security.jwt.JwtProvider;
import com.tienda.ShopServiceAPI.security.service.RolService;
import com.tienda.ShopServiceAPI.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity("Campos mal puestos o email invalido", HttpStatus.BAD_REQUEST);}
        else{
        if(userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity("Ese email ya existe", HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity("Usuario guardado", HttpStatus.CREATED);}
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); //autenticamos al user con el login
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
