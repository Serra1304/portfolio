package es.gtorres.backend.security.payload;

/**
 * Respuesta JWT que contiene el token generado.
 */
public class JwtResponse {
    private String token;

    /**
     * Constructor por defecto de JwtResponse.
     */
    public JwtResponse() {
    }

    /**
     * Constructor de JwtResponse que inicializa el token.
     * @param token El token JWT generado.
     */
    public JwtResponse(String token) {
        this.token = token;
    }

    /**
     * Obtiene el token JWT.
     * @return El token JWT.
     */
    public String getToken() {
        return token;
    }

    /**
     * Establece el token JWT.
     * @param token El token JWT a establecer.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
