package com.chinda.user.domain.iam;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AccessToken {
    private String message;

    public AccessToken(final Long userId, Long expiration, String privateKey) {
        final Date now = new Date();

        Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration));

        claims.put("sub", userId);

        this.message = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public boolean verifySignature(String publicKey) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(this.message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getSubject(String publicKey) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(this.message)
                .getBody();

        return Long.parseLong(claims.get("sub").toString());
    }

}