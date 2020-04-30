package crud.config;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private AuthorityService authorityService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                User loggedInUser = (User) authentication.getPrincipal();
                Collection<Authority> authorities = loggedInUser.getAuthorities();
                if (authorities.contains(authorityService.getAuthorityByName("ROLE_ADMIN"))) {
                    httpServletResponse.sendRedirect("/admin/table");
                } else if (authorities.contains(authorityService.getAuthorityByName("ROLE_USER"))) {
                    Long id = userService.getUserByUsername(loggedInUser.getUsername()).getId();
                    httpServletResponse.sendRedirect("/user/info?id=" + id);
                }
            }
        };
    }

    @Autowired
    public SecurityConfig(UserService userService,
                          AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .hasAnyRole()
            .and()
//                .authorizeRequests()
//                .antMatchers("/admin/*")
//                .hasRole("ADMIN")
//            .and()
//                .authorizeRequests()
//                .antMatchers("/user/*")
//                .hasAnyRole("USER", "ADMIN")
//            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler())
            .and()
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
