package com.auth.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @PostConstruct
    protected void init() {
        jwtSecretKey = Base64.getEncoder()
                .encodeToString(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String issueToken(Long userId, Long socialId) {
        final Date now = new Date();

        final Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 120 * 60 * 1000L)); // TODO: 유효기간 상수로 빼기

        claims.put("userId", userId);
        claims.put("socialId", socialId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public boolean verifyToken(String token) {
        try {
            final Claims claims = getBody(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getJwtContents(String token) {
        final Claims claims = getBody(token);
        return (String) claims.get("userId");
    }
}