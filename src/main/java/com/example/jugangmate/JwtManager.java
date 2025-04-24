package com.example.jugangmate;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class JwtManager {
    private final KeyPair keyPair;
    private final JWKSet jwks;

    public JwtManager() {
        this.keyPair = generateKeyPair();
        this.jwks = new JWKSet(
                new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                        .privateKey(keyPair.getPrivate())
                        .keyUse(KeyUse.SIGNATURE)
                        .algorithm(JWSAlgorithm.RS256)
                        .keyID(UUID.randomUUID().toString())
                        .issueTime(new Date())
                        .build()
        );

        loggingKey(keyPair);
    }

    private KeyPair generateKeyPair() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void loggingKey(KeyPair keyPair) {

        byte[] pubBytes = keyPair.getPublic().getEncoded();
        byte[] prvBytes = keyPair.getPrivate().getEncoded();

        Base64.Encoder encoder = Base64.getEncoder();

        log.info("Public: {}", encoder.encodeToString(pubBytes));
        log.info("Private: {}", encoder.encodeToString(prvBytes));
    }

    public Map<String, Object> getJwks() {
        return jwks.toPublicJWKSet().toJSONObject();
    }

}
