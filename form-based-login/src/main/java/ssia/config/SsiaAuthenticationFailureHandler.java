//: ssia.config.SsiaAuthenticationFailureHandler.java


package ssia.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.time.LocalDateTime;


class SsiaAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        response.setHeader("FAILED_TIME", LocalDateTime.now().toString());

        response.sendRedirect((exception instanceof BadCredentialsException bce)
                ? "/greeting/error" : "/login");
    }

}///:~