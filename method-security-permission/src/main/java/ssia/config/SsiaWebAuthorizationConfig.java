//: ssia.config.SsiaWebAuthorizationConfig.java


package ssia.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ssia.repository.DocumentRepository;
import ssia.service.PreAuthDocumentPermissionEvaluator;
import ssia.service.PostAuthDocumentPermissionEvaluator;


@Slf4j
@Configuration
@EnableMethodSecurity
class SsiaWebAuthorizationConfig {

    @Bean
    PermissionEvaluator postAuthPermissionEvaluator() {
        return new PostAuthDocumentPermissionEvaluator();
    }

    @Bean
    PermissionEvaluator preAuthPermissionEvaluator(
            DocumentRepository documentRepository) {

        return PreAuthDocumentPermissionEvaluator.of(documentRepository);
    }

    @Bean
    MethodSecurityExpressionHandler methodSecurityExpressionHandler(
            PermissionEvaluator preAuthPermissionEvaluator) {

        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(preAuthPermissionEvaluator);
        return expressionHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(
            @NonNull final HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c.anyRequest().authenticated());

        var filterChain = http.build();

        filterChain.getFilters().forEach(
                f -> log.info(">>> Filter : {}", f.getClass().getSimpleName()));

        return filterChain;
    }

}///:~