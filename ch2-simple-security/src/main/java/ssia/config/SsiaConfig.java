//: ssia.config.SsiaConfig.java


package ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class SsiaConfig {

    @Bean
    UserDetails user() {
        return User.withUsername("yul")
                .password("password")
                .authorities("read")
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserDetails user) {
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Makes all the endpoints accessible without the need for credentials
        // http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

}///:~