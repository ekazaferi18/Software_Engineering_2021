package Group2A.SDMS.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsService authenticationDetailsService;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(authenticationDetailsService);
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").permitAll()
                .antMatchers("/addTestUser").permitAll()
                .antMatchers("/").permitAll()
        .and()
                .formLogin()
                .loginPage("/login").permitAll()
        .and()
                .logout()
                .permitAll();
    }
}
