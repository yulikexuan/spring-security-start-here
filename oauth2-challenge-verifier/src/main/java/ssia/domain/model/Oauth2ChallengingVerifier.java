package ssia.domain.model;


import lombok.NonNull;


public record Oauth2ChallengingVerifier(String challenge, String verifier) {

    public static Oauth2ChallengingVerifier of(
            @NonNull final String challenge,
            @NonNull final String verifier) {

        return new Oauth2ChallengingVerifier(challenge, verifier);
    }

}
