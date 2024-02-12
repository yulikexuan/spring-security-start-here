//: ssia.config.SsiaUserManagementConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;


@Configuration
class SsiaUserManagementConfig {

    static final List<String> ALL_ROLES = List.of("MANAGER", "ADMIN", "EMPLOYEE");

    @Bean
    public UserDetailsService userDetailsService() {

        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User.withUsername("yul")
                .password("yul")
                // .authorities("ROLE_ADMIN")
                .roles("ADMIN")
                .build());

        userDetailsManager.createUser(User.withUsername("joel")
                .password("joel")
                // .authorities("ROLE_EMPLOYEE")
                .roles("MANAGER")
                .build());

        userDetailsManager.createUser(User.withUsername("zac")
                .password("zac")
                // .authorities("ROLE_EMPLOYEE")
                .roles("EMPLOYEE")
                .build());

        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}///:~