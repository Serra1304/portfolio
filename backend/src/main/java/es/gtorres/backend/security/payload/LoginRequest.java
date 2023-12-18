package es.gtorres.backend.security.payload;

/**
 * Clase que representa la solicitud de inicio de sesión.
 * Contiene la información de usuario y contraseña para autenticación.
 */
public class LoginRequest {

    private String user;
    private String password;

    /**
     * Constructor por defecto de la clase LoginRequest.
     */
    public LoginRequest() {
    }

    /**
     * Obtiene el nombre de usuario.
     * @return Nombre de usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario.
     * @param user El nombre de usuario a establecer.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene la contraseña.
     * @return La contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña.
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
