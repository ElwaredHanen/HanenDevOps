package com.coeurious.sublimation.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.coeurious.sublimation.entities.Users;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenProvider {
    @Value("${security.jwt.token.secret-key}")
    private String jwtSecretUri;

    @Value("${security.jwt.token.expire_in}")
    private long expireIn;

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    public String generateAccessToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(getSecret(jwtSecretUri));
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("email", user.getUsername())
                    .withExpiresAt(genAccessExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(getSecret(jwtSecretUri));
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    private String getSecret(String secretUri) {
        String path = FilenameUtils.getFullPath(secretUri) + FilenameUtils.getName(secretUri);
        String secret = "";

        try {
            secret = IOUtils.toString(new URI(path), StandardCharsets.UTF_8.name()).trim();
        } catch (Exception e) {
            logger.error("Error reading secret filename on path : " + secretUri, e);
        }
        return secret;
    }

    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusMinutes(expireIn).toInstant(ZoneOffset.of("+01:00"));
    }
}