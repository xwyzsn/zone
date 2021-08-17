package xwyzsn.zone.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xwyzsn.zone.Handler.AuthFailHandler;
import xwyzsn.zone.Handler.AuthSuccessHandler;

@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_URL = {
            "/api/zone/register",
            "/login",
            "/logout",
    };

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailHandler authFailHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity  http) throws Exception {
        http
                .cors().and().csrf().disable()
                .formLogin()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(WHITE_URL).permitAll()
                .anyRequest().authenticated();
    }
}
