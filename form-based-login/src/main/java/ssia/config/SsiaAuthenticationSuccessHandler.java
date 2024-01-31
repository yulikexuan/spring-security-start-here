//: ssia.config.SsiaAuthenticationSuccessHandler.java


package ssia.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


class SsiaAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        boolean isRead = authentication.getAuthorities().stream()
                .anyMatch(a -> "read".equals(a.getAuthority()));

        response.sendRedirect(isRead ? "/greeting/hello" : "/greeting/writing");
    }

}///:~