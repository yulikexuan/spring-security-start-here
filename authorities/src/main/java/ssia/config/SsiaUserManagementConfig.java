//: ssia.config.SsiaConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
class SsiaUserManagementConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User.withUsername("yul")
                .password("yul")
                .authorities("READ", "WRITE", "DELETE")
                .build());

        userDetailsManager.createUser(User.withUsername("joel")
                .password("joel")
                .authorities("READ", "WRITE")
                .build());
        userDetailsManager.createUser(User.withUsername("zac")
                .password("zac")
                .authorities("READ")
                .build());

        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}///:~