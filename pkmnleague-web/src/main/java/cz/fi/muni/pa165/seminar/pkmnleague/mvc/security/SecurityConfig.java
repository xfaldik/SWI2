package cz.fi.muni.pa165.seminar.pkmnleague.mvc.security;

import cz.fi.muni.pa165.seminar.pkmnleague.service.TrainerService;
import cz.fi.muni.pa165.seminar.pkmnleague.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author olda
 */
@Configuration
@Import(ServiceConfiguration.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TrainerService trainerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(trainerService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/gym/create").hasRole("TRAINER")
                .antMatchers("/gym/badge").hasRole("GYMLEADER")
                .antMatchers("/gym/edit").hasRole("GYMLEADER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/", true).permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "favicon.ico", "/img/**");
    }

}
