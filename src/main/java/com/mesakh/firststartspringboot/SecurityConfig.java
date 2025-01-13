package com.mesakh.firststartspringboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void config(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
            Exception{
        httpSecurity.authorizeHttpRequests(authorize->authorize
                .requestMatchers("/","/login","/register")
                .permitAll()
                .anyRequest().authenticated()

        )
        .formLogin(form->form
                        .loginPage("/login")
//                        .defaultSuccessUrl("/admin")
                        .defaultSuccessUrl("/admin/users")
                        .permitAll()
        )
                    .logout(logout->logout.permitAll());
            return  httpSecurity.build();
        }
//private final UserDetailsService
//        userDetailsService;
//    public SecurityConfig(UserDetailsService
//                                  userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    protected void configure(AuthenticationManager authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//    @Bean
//    public SecurityFilterChain
//    filterChain(HttpSecurity httpSecurity) throws
//            Exception{
//        httpSecurity.authorizeHttpRequests(authorize
//                >authorize
//                .requestMatchers("/","/login","/register")
//                .permitAll()
//                .anyRequest().authenticated()
//        )
//)
//.formLogin(form->form
//                .loginPage("/login")
//                .defaultSuccessUrl("/admin")
//                .permitAll()
//                .logout(LogoutConfigurer::permitAll);
//        return  httpSecurity.build();
//    }
//}
}

