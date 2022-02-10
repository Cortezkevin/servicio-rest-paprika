package com.tienda.ShopServiceAPI.security.jwt;

import com.tienda.ShopServiceAPI.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

//se ejecuta por cada operacion, comprueba si es valido el token usando el provider, si es valido permite acceso al recurso
public class JwtTokenFilter extends OncePerRequestFilter { //se ejecuta una vez por cada recurso

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    //obtiene el token de la cabezera
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = this.getToken(request);
            if(token != null && jwtProvider.validateToken(token)){
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("Fail in the method doFilter");
        }
        filterChain.doFilter(request, response);
    }

    //metodo para extraer el token y eliminar la parte bearer y el espacio
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ","");
        return null;
    }
}
