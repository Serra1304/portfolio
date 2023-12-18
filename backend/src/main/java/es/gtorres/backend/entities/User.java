package es.gtorres.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa a un usuario.
 * Almacena información sobre el registro de un usuario, como nombre y contraseña.
 */
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String username;
    private String password;

    /**
     * Constructor por defecto de la clase User.
     */
    public User() {
    }

    /**
     * Constructor de la clase User.
     * Instancia un objeto de la clase User con los parámetros proporcionados.
     * @param username Nombre del usuario.
     * @param password Contraseña del usuario.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Obtiene el ID del usuario.
     * @return El ID del usuario.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return El nombre del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre del usuario.
     * @param username Nombre del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contrasela del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo toString que devuelve información sobre el usuario.
     * @return Una cadena de texto con información del usuario.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
