//: ssia.config.Ssia2ClientConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class Ssia2ClientConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {

        return httpSecurity
                .oauth2Login(Customizer.withDefaults())
                .authorizeHttpRequests(requestConfig ->
                        requestConfig.anyRequest().authenticated())
                .build();
    }

}///:~