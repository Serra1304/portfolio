package es.gtorres.backend.security.config;

import es.gtorres.backend.security.jwt.AuthEntryPointJwt;
import es.gtorres.backend.security.jwt.AuthTokenFilter;
import es.gtorres.backend.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuración de seguridad para la aplicación Spring Boot.
 * Gestiona la seguridad de las rutas y la autenticación de los usuarios.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Value("${SECURITY_ALLOWED_ROUTES}")
    private String allowedRoutes;

    @Value("${SECURITY_SECURED_ROUTES}")
    private String securedRoutes;

    @Value("${CORS_ORIGIN}")
    private String corsOrigin;

    @Value("${CORS_METHOD}")
    private String corsMethod;

    @Value("${CORS_HEADER}")
    private String corsHeader;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    /**
     * Crea un filtro para validar los tokens JWT en las solicitudes.
     * @return Filtro para validar tokens JWT.
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Configuración del administrador de autenticación.
     * @param authConfig Configuración de autenticación.
     * @return Administrador de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Codificador de contraseñas para la seguridad.
     * @return Codificador de contraseñas BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Proveedor de autenticación que utiliza el servicio de detalles del usuario y el codificador de contraseñas.
     * @return Proveedor de autenticación.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Configuración de filtro CORS para permitir peticiones desde orígenes, métodos y cabeceras específicos.
     * @return Filtro CORS configurado.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(corsOrigin);
        config.addAllowedMethod(corsMethod);
        config.addAllowedHeader(corsHeader);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * Configura la cadena de filtros de seguridad para las solicitudes HTTP.
     * @param http Configuración de seguridad HTTP.
     * @return Cadena de filtros de seguridad.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(allowedRoutes.split(",")).permitAll()
                        .requestMatchers(securedRoutes.split(",")).authenticated()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(corsFilter());

        return http.build();
    }
}
