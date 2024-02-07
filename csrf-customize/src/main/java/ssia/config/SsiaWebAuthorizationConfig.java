//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import ssia.domain.entity.Token;
import ssia.repository.DbCsrfTokenRepository;
import ssia.repository.JpaTokenRepository;

import static ssia.config.SsiaUserManagementConfig.ALL_ROLES;


@Slf4j
@Configuration
@ComponentScan(basePackages = {"ssia.web", "ssia.repository"})
class SsiaWebAuthorizationConfig {

    @Bean
    CsrfTokenRepository csrfTokenRepository(
            JpaTokenRepository jpaTokenRepository) {

        return DbCsrfTokenRepository.of(jpaTokenRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            @NonNull final CsrfTokenRepository csrfTokenRepository )
            throws Exception {

        String[] allRoles = ALL_ROLES.toArray(String[]::new);

        // Use ignoringRequestMatchers(String... patterns)
        // http.csrf(c -> c.ignoringRequestMatchers("/ciao"));

        // Use MvcRequestMatcher
        // http.csrf(c -> c.ignoringRequestMatchers(new MvcRequestMatcher(
        //         new HandlerMappingIntrospector(), "/ciao")));

        // Use RegexRequestMatcher
        http.csrf(c -> {
            c.ignoringRequestMatchers(
                    new RegexRequestMatcher("/ciao", HttpMethod.POST.name()));
            c.csrfTokenRepository(csrfTokenRepository);
            // Setting the CsrfTokenRequestAttributeHandler object to manage
            // the set up of the CSRF token on the HTTP request
            c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
        } );

        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c.anyRequest().hasAnyRole(allRoles));

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~