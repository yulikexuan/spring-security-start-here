//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ssia.web.filter.StaticKeyAuthenticationFilter;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"ssia.web.controller", "ssia.web.filter"})
class SsiaWebAuthorizationConfig {

    @Value("${ssia.authorization.key}")
    private String authorizationKey;

    @Bean
    Filter staticKeyAuthenticationFilter() {
        return StaticKeyAuthenticationFilter.of(this.authorizationKey);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http, Filter staticKeyAuthenticationFilter)
            throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());

        DefaultSecurityFilterChain filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter [{}]", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~