package ssia.web.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssia.domain.model.Oauth2ChallengingVerifier;
import ssia.service.Oauth2ChallengeVerifierCodesService;


@Slf4j
@RestController
@RequestMapping("/codes")
@AllArgsConstructor
public class Oauth2ChallengeVerifierController {

    private final Oauth2ChallengeVerifierCodesService oauth2CodesService;

    @GetMapping("/challenge")
    public Oauth2ChallengingVerifier challengeVerifier() {

        Oauth2ChallengingVerifier codes = this.oauth2CodesService.generate();

        log.info(">>> Generated Oauth2 Codes {} ! ", codes);

        return codes;
    }
}