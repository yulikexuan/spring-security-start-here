//: ssia.repository.DbCsrfTokenRepository.java


package ssia.repository;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import ssia.domain.entity.Token;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class DbCsrfTokenRepository implements CsrfTokenRepository {

    static final String CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    static final String CSRF_PARAMETER_NAME = "_csrf";
    static final String TOKEN_IDENTIFIER = "X-IDENTIFIER";

    private final JpaTokenRepository jpaTokenRepository;

    /**
     * Calls this method when the application needs to generate a new token
     * Keep the same names for the request header and attribute,
     * X-CSRF-TOKEN and _csrf, as in the default
     *
     * implementation offered by Spring Security.
     *
     * @param request the {@link HttpServletRequest} to use
     * @return new created {@link CsrfToken} instance
     */
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(CSRF_HEADER_NAME, CSRF_PARAMETER_NAME, uuid());
    }

    /**
     * Saves a generated token for a specific client
     *
     * In the case of the default CSRF protection implementation,
     * the application uses the HTTP session to identify the CSRF token
     *
     * In our case, we assume that the client has a unique identifier.
     * The client sends the value of its unique ID in the request with
     * the header named X-IDENTIFIER.
     *
     * @param csrfToken the {@link CsrfToken} to save or null to delete
     * @param request the {@link HttpServletRequest} to use
     * @param response the {@link HttpServletResponse} to use
     */
    @Override
    public void saveToken(CsrfToken csrfToken,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        String identifier = request.getHeader(TOKEN_IDENTIFIER);

        this.jpaTokenRepository.findTokenByIdentifier(identifier)
                .ifPresentOrElse(
                        t -> {
                            t.setToken(csrfToken.getToken());
                            log.info(">>> Token Updated for Client: {}", identifier);
                        },
                        () -> {
                            this.saveNewToken(identifier, csrfToken);
                            log.info(">>> Saved new Token for Client: {}", identifier);
                        });


    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {

        String identifier = request.getHeader(TOKEN_IDENTIFIER);

        return this.jpaTokenRepository.findTokenByIdentifier(identifier)
                .map(this::createNewCsrfToken)
                .orElse(null);
    }

    void saveNewToken(String identifier, CsrfToken csrfToken) {
        Token token = new Token();
        token.setToken(csrfToken.getToken());
        token.setIdentifier(identifier);
        this.jpaTokenRepository.save(token);
    }

    CsrfToken createNewCsrfToken(Token token) {
        return new DefaultCsrfToken(
                CSRF_HEADER_NAME, CSRF_PARAMETER_NAME, token.getToken());
    }

    static String uuid() {
        return UUID.randomUUID().toString();
    }

}///:~