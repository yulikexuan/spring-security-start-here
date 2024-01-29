//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@Configuration
class SsiaWebAuthorizationConfig {

    @Bean
    Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicConfigurer() {
        return new HttpRealmCustomizer();
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http,
            Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicConfigurer)
            throws Exception {

        http.httpBasic(httpBasicConfigurer)
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~