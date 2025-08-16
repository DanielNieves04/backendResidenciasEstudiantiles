package R.U.R.U.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(publicEndpoints()).permitAll()
                        .anyRequest().authenticated())  //Otros endpoints requieren autenticación
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    //Lista Blanca(Metodos publicos)
    private RequestMatcher publicEndpoints(){
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/User/findAllUsers"),
                new AntPathRequestMatcher("/User/findUserById/**"),
                new AntPathRequestMatcher("/User/findUserByMail/**"),
                new AntPathRequestMatcher("/User/findInfoById/**"),
                new AntPathRequestMatcher("/User/requestReset/**"),
                new AntPathRequestMatcher("/User/requestResetLogin/**"),
                new AntPathRequestMatcher("/User/validateCode/**"),
                new AntPathRequestMatcher("/User/resetPassword/**/**"),
                new AntPathRequestMatcher("/User/saveUser"),
                new AntPathRequestMatcher("/User/authenticateUser"),
                new AntPathRequestMatcher("/Residence/findAllResidences"),
                new AntPathRequestMatcher("/Residence/findResidenceById/**")
        );
    }

}
