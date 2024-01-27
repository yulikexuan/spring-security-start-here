//: ssia.config.Ssia2AppConfig.java


package ssia.config;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL;
import static org.springframework.security.core.context.SecurityContextHolder.setStrategyName;


@EnableAsync
@Configuration
class Ssia2AppConfig {

    @Bean
    InitializingBean initializingBean() {
        return () -> setStrategyName(MODE_INHERITABLETHREADLOCAL);
    }

}///:~