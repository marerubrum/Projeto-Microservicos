package br.com.cursomensageria.eurekaserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/eureka/**").hasRole("EUREKA")  // Acesso somente para serviços Eureka
                        .anyRequest().authenticated()
                )
                .httpBasic()  // Habilitar autenticação básica
                .and()
                .csrf().disable();  // Desabilitar CSRF para permitir a comunicação entre os serviços

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("cursoms-eureka-user")
                        .password("{noop}ecok-usr-eeuramssur")  // Definindo a senha (noop para sem encriptação)
                        .roles("EUREKA")  // Definindo o papel (role)
                        .build()
        );
    }
}
