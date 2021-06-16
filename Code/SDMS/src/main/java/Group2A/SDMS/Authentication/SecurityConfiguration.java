package Group2A.SDMS.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

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
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/userManagement/**").fullyAuthenticated()
                .antMatchers("/addTestUser").permitAll()
                .antMatchers("/productManagement/**").fullyAuthenticated()
                .antMatchers("/categoryManagement/**").fullyAuthenticated()
                .antMatchers("/viewOrders/**").fullyAuthenticated()
                .antMatchers("/userProfile/**").fullyAuthenticated()
                .antMatchers("/").fullyAuthenticated()
        .and()
                .formLogin().permitAll()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=true")
        .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
