//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class SsiaWebAuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Makes all the endpoints accessible without the need for credentials
        // http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

}///:~