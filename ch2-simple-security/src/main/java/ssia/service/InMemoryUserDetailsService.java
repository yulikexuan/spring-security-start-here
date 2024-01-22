//: ssia.service.InMemoryUserDetailsService.java


package ssia.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username)
            throws UsernameNotFoundException {

        UserDetails loginUser = users.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(
                        ">>> No user has username %s".formatted(username)));

        log.info(">>> User {} logged in.", loginUser.getUsername());

        return loginUser;
    }

}///:~