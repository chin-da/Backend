package com.auth.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.public-key}")
    private String publicKey;

    @Value("${jwt.private-key}")
    private String privateKey;

    private static final int jwtExpiration = 120 * 60;

    public String issueToken(final Long userId) {
        final Date now = new Date();

        Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration * 1000L));

        claims.put("sub", userId);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getJwtSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.get("sub").toString());
    }

}