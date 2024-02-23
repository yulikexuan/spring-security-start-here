//: ssia.service.Oauth2ChallengeVerifierCodesService


package ssia.service;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ssia.domain.model.Oauth2ChallengingVerifier;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public interface Oauth2ChallengeVerifierCodesService {

    String DEFAULT_ALGORITHM = "SHA-256";

    Oauth2ChallengingVerifier generate();

    static Oauth2ChallengeVerifierCodesService of() {
        return new Oauth2ChallengeVerifierCodesServiceImpl();
    }
}

@Slf4j
class Oauth2ChallengeVerifierCodesServiceImpl
        implements Oauth2ChallengeVerifierCodesService {

    @Override
    public Oauth2ChallengingVerifier generate() {

        final var verifier = this.generateVerifier();
        final var challenge = this.generateChallenge(verifier);

        return Oauth2ChallengingVerifier.of(challenge, verifier);
    }

    String generateVerifier() {
        SecureRandom secureRandom = new SecureRandom();
        byte [] code = new byte[32];
        secureRandom.nextBytes(code);
        String codeVerifier = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(code);

        log.info(">>> Generates Code Verifier: {}", codeVerifier);

        return codeVerifier;
    }

    String generateChallenge(@NonNull final String verifier) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(
                    DEFAULT_ALGORITHM);

            byte [] digested = messageDigest.digest(verifier.getBytes());

            String codeChallenge = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(digested);

            log.info(">>> Generates Challenge: {}", codeChallenge);

            return codeChallenge;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

///:~