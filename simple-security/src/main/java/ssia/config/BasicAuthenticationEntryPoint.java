//: ssia.config.BasicAuthenticationEntryPoint.java


package ssia.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


/*
 * In the Spring Security architecture, this is used directly by a component
 * called ExceptionTranslationManager,
 * which handles any AccessDeniedException and AuthenticationException
 * thrown within the filter chain
 */
class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        response.addHeader("AUTHENTICATION-MESSAGES",
                ">>> LUCK, I AM YOUR FATHER!");
        response.sendError(HttpStatus.UNAUTHORIZED.value());

    }

}///:~