package vn.shortsoft.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import vn.shortsoft.userservice.filter.JwtAuthFilter;
import vn.shortsoft.userservice.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/v1/public/*")
                .permitAll()
                .requestMatchers("/v1/user/*").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.POST, "/v1/admin/*").hasRole("ADMIN")
                .requestMatchers("/v1/user/*").hasAnyAuthority("USER")
                .anyRequest()
                .authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/v1/user/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler()).invalidateHttpSession(true))
                .httpBasic(Customizer.withDefaults());
        // httpSecurity.oauth2Login(Customizer.withDefaults());
        // httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
        // jwt.decoder(jwtDecoder())));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
    // {
    // UserDetails user = User.builder()
    // .username("tai1")
    // .password(passwordEncoder.encode("123"))
    // .roles("USER")
    // .build();
    // return new InMemoryUserDetailsManager(user);
    // }

    // @Bean
    // public JwtDecoder jwtDecoder() {
    // SecretKeySpec secretKeySpec = new
    // SecretKeySpec(JwtContant.SECRET_KEY.getBytes(),
    // MacAlgorithm.HS512.getName());
    // return NimbusJwtDecoder.withSecretKey(secretKeySpec)
    // .macAlgorithm(MacAlgorithm.HS512)
    // .build();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
