package de.dental_clinic.g_43_praxis.security.sec_config;

import de.dental_clinic.g_43_praxis.security.sec_filter.TokenFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
    private TokenFilter tokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/service").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/doctors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/appointments").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/image").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/services", "/api/doctors", "/api/appointments").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/service", "/api/doctors", "/api/appointments", "/api/password").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/service/{id}", "/api/doctors/{id}", "/api/appointments/{id}", "/api/image/{d}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("1"));
//        System.out.println(new BCryptPasswordEncoder().encode("1"));
//    }

// password (1)->
// $2a$10$Vii5olNUzeMlE0gd/i/buegOYubZC38tfWmq8uDMSaGOmpkgz4fiC
// $2a$10$vGF9Q86.lNiRP0fuLDSSielT18l7YrCF45AKavDasPHASh9yiA9ua