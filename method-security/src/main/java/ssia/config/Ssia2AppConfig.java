//: ssia.config.Ssia2AppConfig.java


package ssia.config;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ssia.service.BookService;
import ssia.service.NameService;

import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL;
import static org.springframework.security.core.context.SecurityContextHolder.setStrategyName;


@Configuration
@ComponentScan(basePackages = {"ssia.web"})
class Ssia2AppConfig {

    @Bean
    InitializingBean initializingBean() {

        /*
         * This mode can not only copy security contest to Spring managed threads,
         * but also self-managed (app managed) threads
         * Seems, DelegatingSecurityContextCallable is not needed anymore
         */
        return () -> setStrategyName(MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    NameService authorizedNameService() {
        return NameService.authorizedNameService();
    }

    @Bean
    BookService bookService() {
        return BookService.of();
    }

}///:~