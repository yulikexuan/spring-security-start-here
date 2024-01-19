//: ssia.config.SsiaConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ssia.security.SsiaAuthenticationProvider;


@Configuration
@ComponentScan(basePackages = {"/ssia/controllers", "/ssia/security"})
class SsiaConfig {

    @Bean
    AuthenticationProvider authenticationProvider() {
        return new SsiaAuthenticationProvider();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Makes all the endpoints accessible without the need for credentials
        // http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

}///:~