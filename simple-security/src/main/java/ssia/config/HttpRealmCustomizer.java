//: ssia.config.HttpRealmCustomizer.java


package ssia.config;


import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;


class HttpRealmCustomizer implements Customizer<HttpBasicConfigurer<HttpSecurity>> {

    @Override
    public void customize(HttpBasicConfigurer<HttpSecurity> basicConfig) {

        /*
         * HTTP response in which the realm name is indeed changed.
         * However, note that the WWW-Authenticate header in the response only
         * displayed when the HTTP response status is 401 Unauthorized and not
         * when the HTTP response status is 200 OK
         *
         * Do this if the client of the system expects something specific in the
         * response in the case of a failed authentication (401)
         */
        basicConfig.realmName("STAR-WAR");

        /*
         * This will override line basicConfig.realmName("STAR-WAR");
         */
        basicConfig.authenticationEntryPoint(new BasicAuthenticationEntryPoint());
    }

}///:~