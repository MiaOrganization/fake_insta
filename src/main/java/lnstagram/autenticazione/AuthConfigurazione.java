package lnstagram.autenticazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * The AuthConfiguration is a Spring Security Configuration.
 * It extends WebSecurityConfigurerAdapter, meaning that it provides the settings for Web security.
 */
@Configuration
@EnableWebSecurity
public class AuthConfigurazione extends WebSecurityConfigurerAdapter {

    /**
     * The datasource is automatically injected into the AuthConfiguration (using its getters and setters)
     * and it is used to access the DB to get the Credentials to perform authentiation and authorization
     */
    @Autowired
    DataSource datasource;

    /**
     * This method provides the whole authentication and authorization configuration to use.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // authorization paragraph: qui definiamo chi può accedere a cosa
                .authorizeRequests()
                // chiunque (autenticato o no) può accedere alle pagine index, login, register, ai css e alle immagini
                .antMatchers(HttpMethod.GET, "/", "/index", "/login","/loginSMS", "/register", "/artisti", "/artista/{id}", "/opere",
                        "/opera/{id}", "/collezioni", "/collezione/{id}", "/logout", "/css/**", "/images/**", "/informazioni").permitAll()
                // chiunque (autenticato o no) può mandare richieste POST al punto di accesso per login e register
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                // solo gli utenti autenticati con ruolo ADMIN possono accedere a risorse con path /admin/**
                // tutti gli utenti autenticati possono accere alle pagine rimanenti 
                .anyRequest().authenticated()

                // login paragraph: qui definiamo come è gestita l'autenticazione
                // usiamo il protocollo formlogin 
                .and().formLogin()
                // la pagina di login si trova a /login
                // NOTA: Spring gestisce il post di login automaticamente
                .loginPage("/login")
                // se il login ha successo, si viene rediretti al path /default
                .defaultSuccessUrl("/default")

                // logout paragraph: qui definiamo il logout
                .and().logout().
                logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
    }
}