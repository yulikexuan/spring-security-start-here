//: ssia.web.filter.RequestValidationFilter.java


package ssia.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class RequestValidationFilter extends OncePerRequestFilter {

    private final String headerName;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String requestId = request.getHeader(headerName);

        if (StringUtils.isBlank(requestId)) {
            log.error(">>> No Correct Header Set Up");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        } else {
            log.info(">>> Will Authenticate Request with Request-Id : {}",
                    requestId);
            filterChain.doFilter(request, response);
        }

    }

}///:~