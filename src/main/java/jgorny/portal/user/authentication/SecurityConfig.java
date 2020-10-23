package jgorny.portal.user.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/branches/**").hasRole("User")
                .antMatchers(HttpMethod.PUT, "/api/branches/**").hasRole("Admin")
                .antMatchers(HttpMethod.POST, "/api/branches/**").hasRole("Admin");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("User").and()
                .withUser("admin").password("{noop}admin").roles("Admin", "User");
    }

}
