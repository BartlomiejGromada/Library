package pl.gromada.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*/addForm").hasRole("ADMIN")
                .antMatchers("/*/updateForm/*").hasRole("ADMIN")
                .antMatchers("/*/delete/*").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                        .permitAll()
                    .loginProcessingUrl("/login")
                        .permitAll()
                    .defaultSuccessUrl("/books").permitAll()
                .and()
                .logout()
                    .permitAll()
                .logoutSuccessUrl("/login")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("bartek@gmail.com").password("{noop}password1").roles("ADMIN")
                .and()
                .withUser("iwona@gmail.com").password("{noop}password2").roles("USER");
    }
}
