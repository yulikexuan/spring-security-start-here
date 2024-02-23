//: ssia.config.ResourceServerConfig.java


package ssia.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class Ssia2ResourceServerConfig {

    @Value("${key.set.uri}")
    private String keySetUri;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Config the Authentication for this Resource Server
        return httpSecurity
                .oauth2ResourceServer(resSrvCfg ->
                        resSrvCfg.jwt(jwt -> jwt.jwkSetUri(keySetUri)))
                .authorizeHttpRequests(reqMatcherReg ->
                        reqMatcherReg.anyRequest().authenticated())
                .build();
    }

}///:~