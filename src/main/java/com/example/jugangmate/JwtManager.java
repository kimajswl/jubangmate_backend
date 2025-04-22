package com.example.jugangmate;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

@Slf4j
@Component
@AllArgsConstructor
public class JwtManager {
    private final KeyPair keyPair;
    private final JWKSet jwks;

}
