package jgorny.portal.user.authentication;

import jgorny.portal.user.authentication.service.UserProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserProviderService service;

    @Autowired
    public SecurityConfig(UserProviderService service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().csrf().disable().cors().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/branches/**").hasRole("User")
                .antMatchers(HttpMethod.PUT, "/api/branches/**").hasRole("Admin")
                .antMatchers(HttpMethod.POST, "/api/branches/**").hasRole("Admin");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(service);
        auth.authenticationProvider(provider);
    }
}
