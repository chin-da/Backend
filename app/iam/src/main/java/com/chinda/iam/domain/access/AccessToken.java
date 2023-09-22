package com.chinda.iam.domain.access;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class AccessToken {
    private String message;
    @Value("${jwt.private-key-pem}")
    private String privateKeyPEM;
    private static final String PRIVATE_KEY_PREFIX = "-----BEGIN PRIVATE KEY-----";
    private static final String PRIVATE_KEY_SUFFIX = "-----END PRIVATE KEY-----";
    private static final String EMPTY_STRING = "";

    private RSAPrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyBase64 = privateKeyPEM.replace(PRIVATE_KEY_PREFIX, EMPTY_STRING)
                .replaceAll(System.lineSeparator(), EMPTY_STRING)
                .replace(PRIVATE_KEY_SUFFIX, EMPTY_STRING);

        byte[] encoded = Base64.decodeBase64(privateKeyBase64);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);

        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    public AccessToken(final Long userId, Long expiration) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPrivateKey privateKey = getPrivateKey();
        final Date now = new Date();

        Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiration));

        claims.put("sub", userId);

        this.message = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean verifySignature(RSAPublicKey publicKey) {
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

    public long getSubject(RSAPublicKey publicKey) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(this.message)
                .getBody();

        return Long.parseLong(claims.get("sub").toString());
    }

}