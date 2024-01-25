//: ssia.web.filter.StaticKeyAuthenticationFilter.java


package ssia.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.IOException;


@RequiredArgsConstructor(staticName = "of")
public class StaticKeyAuthenticationFilter implements Filter {

    @NonNull
    private final String authorizationKey;

    @Override
    public void doFilter(@NonNull final ServletRequest servletRequest,
                         @NonNull final ServletResponse servletResponse,
                         @NonNull final FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        final var staticKey = req.getHeader("Authorization");

        if (!authorizationKey.equals(staticKey)) {
            ((HttpServletResponse) servletResponse).setStatus(
                    HttpStatus.UNAUTHORIZED.value());
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}///:~