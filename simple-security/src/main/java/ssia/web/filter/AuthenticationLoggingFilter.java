//: ssia.web.filter.AuthenticationLoggingFilter.java


package ssia.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class AuthenticationLoggingFilter implements Filter {

    private final String headerName;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        var requestId = ((HttpServletRequest)servletRequest).getHeader(
                this.headerName);

        log.info(">>> Successfully authenticated request with id: {}", requestId);

        filterChain.doFilter(servletRequest, servletResponse);
    }

}///:~