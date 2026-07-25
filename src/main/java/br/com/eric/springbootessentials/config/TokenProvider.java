package br.com.eric.springbootessentials.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.key}")
    private String key;

    // Gerar Token
    public String gerarToken (Authentication authentication)
    {
        UserDetails user = (UserDetails) authentication.getPrincipal();

        return buildToken(user.getUsername());
    }

    private String buildToken (String username)
    {
        Date dateNow = new Date();
        Date expiration = new Date(dateNow.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .expiration(expiration)
                .issuedAt(dateNow)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey ()
    {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // Validar Token
    public boolean isTokenValid (String token)
    {
        try{
            getClaims(token);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    private Claims getClaims (String token)
    {
        // Validar Assinatura do Toke
        // Validar expiração do Token
        return  Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    //Extrair informações do Token
    public String getUsername (String token)
    {
        return getClaims(token).getSubject();
    }
}
