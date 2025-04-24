package com.example.jugangmate;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

public class JwtValidator {
    private final ConfigurableJWTProcessor<SecurityContext> jwtProcessor;

    public JwtValidator(JWKSet jwkSet) {
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
        jwtProcessor = new DefaultJWTProcessor<>();
        jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource));
    }

    public JWTClaimsSet validate(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return jwtProcessor.process(signedJWT, null);
    }
}
