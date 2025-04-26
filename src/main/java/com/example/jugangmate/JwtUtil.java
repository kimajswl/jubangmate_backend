package com.example.jugangmate;

import com.example.jugangmate.form.UserForm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {
    private final RSAKey rsaKey;

    @Value("${jwt.access-token-expire-seconds}")
    private long accessTokenExpireSeconds;

    @Value("${jwt.refresh-token-expire-seconds}")
    private long refreshTokenExpireSeconds;

    public JwtUtil(
            @Value("${jwt.access-token-expire-seconds}") long accessTokenExpireSeconds,
            @Value("${jwt.refresh-token-expire-seconds}") long refreshTokenExpireSeconds
    ) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();

            this.rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                    .privateKey((RSAPrivateKey) keyPair.getPrivate())
                    .keyID(UUID.randomUUID().toString())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA keys", e);
        }
        this.accessTokenExpireSeconds = accessTokenExpireSeconds;
        this.refreshTokenExpireSeconds = refreshTokenExpireSeconds;
    }

    public String createAccessToken(UserForm userForm) {
        return createToken(userForm, accessTokenExpireSeconds);
    }

    public String createRefreshToken(UserForm userForm) {
        return createToken(userForm, refreshTokenExpireSeconds);
    }

    private String createToken(UserForm userForm, long expireSeconds) {
        try {
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime expiry = now.plusSeconds(expireSeconds);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userForm.getEmail())  // 여기 email을 주로 subject로 삼을 수 있음
                    .claim("name", userForm.getName())
                    .claim("email", userForm.getEmail())
                    .claim("role", userForm.getUserRole())
                    .issueTime(Date.from(now.toInstant()))
                    .expirationTime(Date.from(expiry.toInstant()))
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.RS256)
                            .keyID(rsaKey.getKeyID())
                            .build(),
                    claimsSet
            );

            RSASSASigner signer = new RSASSASigner(rsaKey.toPrivateKey());
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JWT", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            RSASSAVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());
            return signedJWT.verify(verifier);
        } catch (Exception e) {
            log.error("JWT validation failed", e);
            return false;
        }
    }

    public String getSubject(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JWT", e);
        }
    }

    public RSAKey getPublicKey() {
        return rsaKey.toPublicJWK();
    }
}
