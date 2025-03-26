//package de.dental_clinic.g_43_praxis.config;
//
//import de.dental_clinic.g_43_praxis.domain.entity.CustomAdminDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomAdminDetailsService adminDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    public SecurityConfig(CustomAdminDetailsService adminDetailsService, PasswordEncoder passwordEncoder) {
//        this.adminDetailsService = adminDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailsService).passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Вимикає CSRF для базової авторизації (не рекомендовано в продакшені)
//                .authorizeRequests()
//                .antMatchers("/api/admin/**").hasRole("ADMIN") // Доступ тільки для ролі ADMIN
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic(); // Використовує HTTP Basic Authentication
//    }
//}
