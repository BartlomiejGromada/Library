package pl.gromada.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.gromada.library.profiles.ProdProfile;

import javax.sql.DataSource;

@ProdProfile
@Configuration
public class ProdSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, 'true' FROM account WHERE email=?")
                .authoritiesByUsernameQuery("SELECT email as username, role FROM account_roles INNER JOIN account" +
                        " ON account.id_account = account_roles.account_id_account INNER JOIN account_role ON" +
                        " account_role.id_user_role = account_roles.roles_id_user_role WHERE email=?");
    }
}
