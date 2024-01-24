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

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class RequestValidationFilter implements Filter {

    private final String headerName;

    @Override
    public void doFilter(
            @NonNull final ServletRequest servletRequest,
            @NonNull final ServletResponse servletResponse,
            @NonNull final FilterChain filterChain)
            throws IOException, ServletException {

        var httpReq = (HttpServletRequest) servletRequest;
        var httpResp = (HttpServletResponse) servletResponse;

        String requestId = httpReq.getHeader(headerName);

        if (StringUtils.isBlank(requestId)) {
            log.error(">>> No Correct Header Set Up");
            httpResp.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        } else {
            log.info(">>> Will Authenticate Request with Request-Id : {}",
                    requestId);
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}///:~