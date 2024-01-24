//: ssia.config.SsiaConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import ssia.repository.UserQueries;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"ssia.web.controller", "ssia.web.filter"})
class SsiaUserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService(final DataSource dataSource) {

        var userDetailsManager = new JdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(
                UserQueries.SQL_FIND_USER_BY_USERNAME);

        userDetailsManager.setAuthoritiesByUsernameQuery(
                UserQueries.SQL_FIND_AUTHORITY_BY_USERNAME);

        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}///:~