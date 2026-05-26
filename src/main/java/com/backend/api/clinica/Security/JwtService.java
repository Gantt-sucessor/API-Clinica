package com.backend.api.clinica.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "fazendo-teste-completo-para-ver-se-pega-certinho-a-fita-do-jwt";

    public String gerarToken(UserDetails userDetails) {
        return Jwts.builder()
                //Coloca o email/username no payload
                .subject(userDetails.getUsername())
                //Registra o momento atual como hora de criação
                .issuedAt(new Date())
                //Define quando expira. O cálculo 1000 * 60 * 60 * 24 é milissegundos × segundos × minutos × horas = 24 horas
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                //Assina com a chave secreta
                .signWith(getChave())
                //Gera a string final xxxxx.yyyyy.zzzzz
                .compact();
    }

    public String extrairUsername(String token){
        //Aqui está lendo, não construindo
        return Jwts.parser()
                //Usa a mesma chave para ver se a assinatura bate
                .verifyWith(getChave())
                .build()
                //Abre o token e lê o conteúdo
                .parseSignedClaims(token)
                //Pega a parte 2 (onde estão os dados)
                .getPayload()
                //Pega o sub, que é o email que você colocou no gerarToken
                .getSubject();
    }

    public boolean tokenValido(String token, UserDetails userDetails) {
        String username = extrairUsername(token);
        //Vai retornar se o username dentro do token é o mesmo do usuário tentando acessar
        //Vê se o token não expirou tambem
        return username.equals(userDetails.getUsername()) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token){
        Date expiracao = Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        //Se essa data de expiração já passou o token está vencido
        return expiracao.before(new Date());
    }

    private SecretKey getChave(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
