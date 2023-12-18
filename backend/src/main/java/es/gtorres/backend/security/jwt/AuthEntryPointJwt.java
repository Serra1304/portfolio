package es.gtorres.backend.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Esta clase maneja el punto de entrada para excepciones de autenticación.
 * Proporciona respuestas personalizadas dependiendo del tipo de error de autenticación.
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    /**
     * Maneja las excepciones de autenticación y genera una respuesta personalizada según el tipo de error.
     * @param request Objeto HttpServletRequest.
     * @param response Objeto HttpServletResponse.
     * @param authException Excepción de autenticación.
     * @throws IOException Excepción de entrada/salida.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");

        if (authException instanceof BadCredentialsException) {
            body.put("message", "Credenciales inválidas");
        } else if (authException instanceof DisabledException) {
            body.put("message", "La cuenta está deshabilitada");
        } else {
            body.put("message", "Error de autenticación");
        }
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
