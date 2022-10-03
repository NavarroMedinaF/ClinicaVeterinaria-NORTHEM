package com.mindhub.finalProject.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class WebAuthorization extends WebSecurityConfigurerAdapter {

@Override
protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
            .antMatchers("/**").permitAll();

/*

            .antMatchers(HttpMethod.GET, "/api/data","/api/veterinaries/current").permitAll()
            .antMatchers(HttpMethod.GET, "/api/data").permitAll()
            .antMatchers(HttpMethod.GET, "/static/assets").permitAll()
            .antMatchers(HttpMethod.GET,"/api/veterinaries","/api/category").permitAll()
            .antMatchers(HttpMethod.GET, "/api/clients/**","/api/pets/**"
                    ,"/api/prepaid/{id}","/api/cards","/api/cards/{id}","/api/prepaid","/api/category/{id}").hasAnyAuthority("VETERINARY","CLIENT","ADMIN")
            .antMatchers(HttpMethod.GET,"/api/products/**", "/api/toys/**",  "/api/vaccines/**",
    "/api/balancedmeals/**").hasAnyAuthority("VETERINARY","CLIENT","ADMIN")
            .antMatchers(HttpMethod.GET, "/api/current/clients").hasAuthority("CLIENT")



            .antMatchers(HttpMethod.POST, "/api/veterinaries", "/api/admin/product",
                    "api/admin/category").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/hospitalization","/api/clients/pets").hasAuthority("VETERINARY")
            .antMatchers(HttpMethod.POST,  "/api/pets/shifts").hasAnyAuthority("VETERINARY", "CLIENT")
            .antMatchers(HttpMethod.POST,  "/api/cards", "/api/clients/purchases",
                    "/api/clients/prepaid","/api/clients/shifts").hasAuthority("CLIENT")
            .antMatchers(HttpMethod.POST, "/api/clients").permitAll()


            .antMatchers(HttpMethod.PATCH,"/api/admin/product").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PATCH,"/api/hospitalization/{id}","/api/clients/pets","/api/medicalHistory/{id}").hasAuthority("VETERINARY")
            .antMatchers(HttpMethod.PATCH,  "/api/clients/prepaid/{id}","/api/clients").hasAuthority("CLIENT")


            .antMatchers(HttpMethod.DELETE,"/api/client/{id}", "/api/veterinaries/{id}","/api/admin/product/{id}").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.DELETE,"/api/clients/pets").hasAuthority("VETERINARY")
            .antMatchers(HttpMethod.DELETE,"/api/cards","/api/clients/prepaid").hasAuthority("CLIENT");


*/





    http.formLogin()

            .usernameParameter("email")

            .passwordParameter("password")

            .loginPage("/api/login");


    http.logout().logoutUrl("/api/logout");

    // turn off checking for CSRF tokens
    http.csrf().disable();

    //disabling frameOptions so h2-console can be accessed
    http.headers().frameOptions().disable();

    // if user is not authenticated, just send an authentication failure response
    http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if login is successful, just clear the flags asking for authentication
    http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

    // if login fails, just send an authentication failure response
    http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

    // if logout is successful, just send a success response
    http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
}
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
