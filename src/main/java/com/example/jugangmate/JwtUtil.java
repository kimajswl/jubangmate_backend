package com.example.jugangmate;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class JwtUtil {
    private final KeyPair keyPair;
    private final JWKSet jwks;
    private final RSAKey rsaKey;

    private KeyPair generateKeyPair() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 프로그램 실행 되면 constructor에서 생성한 pub, prv key를 출력
    private void loggingKey(KeyPair keyPair) {

        byte[] pubBytes = keyPair.getPublic().getEncoded();
        byte[] prvBytes = keyPair.getPrivate().getEncoded();

        Base64.Encoder encoder = Base64.getEncoder();

        log.info("Public: {}", encoder.encodeToString(pubBytes));
        log.info("Private: {}", encoder.encodeToString(prvBytes));
    }

    // JWKS를 반환하는 메서드
    public Map<String, Object> getJwks() {
        return jwks.toPublicJWKSet().toJSONObject();
    }
}
