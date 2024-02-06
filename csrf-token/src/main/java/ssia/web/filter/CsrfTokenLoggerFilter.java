//: ssia.web.filter.CsrfTokenLoggerFilter.java


package ssia.web.filter;


import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;


@Slf4j
public class CsrfTokenLoggerFilter implements Filter {

    static final String CSRF_TOKEN_ATTRIBUTE_NAME = "_csrf";

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {

        final var token = ((CsrfToken) (servletRequest.getAttribute(
                CSRF_TOKEN_ATTRIBUTE_NAME))).getToken();

        log.info(">>> CSRF Token : '{}' ", token);
    }

}///:~