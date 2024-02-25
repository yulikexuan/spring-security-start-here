//: ssia.web.controller.HomeController.java


package ssia.web.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static ssia.config.Ssia2ClientConfig.OAUTH2_CLIENT_ID;
import static ssia.config.Ssia2ClientConfig.OAUTH2_CLIENT_REGISTRATION_ID;


@RestController
@AllArgsConstructor
class OAuth2ClientController {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @GetMapping("/oauth2/token")
    ResponseEntity<OAuth2AccessToken> oauth2Token() {

        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId(OAUTH2_CLIENT_REGISTRATION_ID)
                .principal(OAUTH2_CLIENT_ID)
                .build();

        var client = this.oAuth2AuthorizedClientManager.authorize(request);

        return ResponseEntity.ok(client.getAccessToken());
    }

}///:~