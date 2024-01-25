//: ssia.web.filter.AuthenticationLoggingFilter.java


package ssia.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    private final String headerName;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        var requestId = request.getHeader(this.headerName);

        log.info(">>> Successfully authenticated request with id: {}", requestId);

        filterChain.doFilter(request, response);
    }

}///:~