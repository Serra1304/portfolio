package es.gtorres.backend.security.payload;

/**
 * Clase que representa una solicitud de registro.
 * Utilizada para capturar y manejar los datos de registro de un usuario.
 */
public class RegisterRequest {
    private String username;
    private String password;

    /**
     * Obtiene el nombre de usuario de la solicitud de registro.
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario en la solicitud de registro.
     * @param username El nombre de usuario a establecer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña de la solicitud de registro.
     * @return La contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña en la solicitud de registro.
     * @param password La contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
