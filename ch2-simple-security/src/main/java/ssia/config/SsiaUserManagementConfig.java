//: ssia.config.SsiaConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ssia.domain.model.User;
import ssia.service.InMemoryUserDetailsService;

import java.util.List;


@Configuration
class SsiaUserManagementConfig {

    static final List<UserDetails> USERS = List.of(
            User.of("yul", "yul", "READ"),
            User.of("joel", "joel", "READ_AND_WRITE"));

    @Bean
    public UserDetailsService userDetailsService() {
        return InMemoryUserDetailsService.of(USERS);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}///:~