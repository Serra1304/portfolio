package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.User;
import es.gtorres.backend.repositories.UserRepository;
import es.gtorres.backend.security.jwt.JwtTokenUtil;
import es.gtorres.backend.security.payload.JwtResponse;

import es.gtorres.backend.security.payload.LoginRequest;
import es.gtorres.backend.security.payload.MessageResponse;
import es.gtorres.backend.security.payload.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la autenticación y registro de usuarios.
 * Maneja las solicitudes relacionadas con el inicio de sesión (login) y registro (register).
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * Constructor de la clase AuthController.
     * @param authManager Gestor de autenticación de Spring Security.
     * @param userRepository Repositorio para operaciones relacionadas con los usuarios.
     * @param encoder Codificador de contraseñas.
     * @param jwtTokenUtil Utilidad para generar y validar tokens JWT.
     */
    public AuthController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * Endpoint que maneja las solicitudes de inicio de sesión (login) de los usuarios.
     * @param loginRequest Solicitud de inicio de sesión con nombre de usuario y contraseña.
     * @return Respuesta con el token JWT si la autenticación es exitosa.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUser(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Endpoint para las solicitudes de registro (register) de nuevos usuarios.
     * @param signUpRequest Solicitud de registro con nombre de usuario y contraseña.
     * @return Respuesta indicando el éxito del registro o el error si el nombre de usuario ya existe.
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El usuario ya existe en la base de datos"));
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
    }
}