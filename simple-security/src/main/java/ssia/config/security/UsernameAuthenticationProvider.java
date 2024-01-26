//: ssia.config.security.UsernameAuthenticationProvider.java


package ssia.config.security;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(
            @NonNull final Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.debug(">>> Authentication Request with {}:{}", username, password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            log.debug(">>> Credential Matched!");
            return UsernamePasswordAuthenticationToken.authenticated(
                    username, password, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException(">>> ");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        log.debug(">>> Calling AuthenticationProvider::supports to check Authentication Type");
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }

}///:~