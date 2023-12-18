package es.gtorres.backend.security.payload;

/**
 * Clase que representa una respuesta de mensaje genérico.
 * Utilizada para devolver mensajes simples como respuesta a las solicitudes.
 */
public class MessageResponse {
    private String message;

    /**
     * Constructor de la clase MessageResponse.
     * @param message El mensaje a almacenar.
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    /**
     * Obtiene el mensaje almacenado en la respuesta.
     * @return El mensaje.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje en la respuesta.
     * @param message El mensaje a establecer.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
