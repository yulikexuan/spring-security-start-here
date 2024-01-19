//: ssia.config.SsiaSecurityConfig.java


package ssia.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Deprecated
// @Configuration
class SsiaSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        // Makes all the endpoints accessible without the need for credentials:
        //        http.authorizeRequests()
        //                .anyRequest()
        //                // .authenticated();
        //                .permitAll();
    }

}///:~