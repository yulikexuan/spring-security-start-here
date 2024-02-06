//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ssia.web.filter.CsrfTokenLoggerFilter;

import static ssia.config.SsiaUserManagementConfig.ALL_ROLES;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"ssia.web"})
class SsiaWebAuthorizationConfig {

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http) throws Exception {

        String[] allRoles = ALL_ROLES.toArray(String[]::new);

        http.formLogin(c -> c.defaultSuccessUrl(
                "/main", true))
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~