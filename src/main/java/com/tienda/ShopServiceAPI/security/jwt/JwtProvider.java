package com.tienda.ShopServiceAPI.security.jwt;

import com.tienda.ShopServiceAPI.security.entity.User;
import com.tienda.ShopServiceAPI.security.entity.UserMain;
import com.tienda.ShopServiceAPI.security.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

//clase que genera el token, metodo de validacion
@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //toma los valores del application properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserMain userMain = (UserMain) authentication.getPrincipal();
        return Jwts.builder().setSubject(userMain.getUsername())
                .setIssuedAt(new Date()) //fecha de creacion
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))//fecha de expiration desde la fecha actual hasta la fecha actual mas en tiempo
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Malformed Token");
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported Token" + e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired Token");
        }catch (IllegalArgumentException e){
            logger.error("Empty Token");
        }catch (SignatureException e){
            logger.error("Fail at the signature");
        }
        return false;
    }
}

