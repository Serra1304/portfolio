package es.gtorres.backend.entities;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa un certificado.
 * Almacena información sobre un certificado como descripción, fecha, imágenes y dirección url del certificado.
 */
@Document(collection = "certificates")
public class Certificate {

    @Id
    private String id;

    @NotBlank(message = "La descripción del certificado es obligatoria.")
    private String description;

    @NotBlank(message = "La fecha del certificado es obligatoria.")
    private String date;

    @NotBlank(message = "La dirección de la imagen del certificado es obligatoria.")
    private String imageUrl;

    @NotBlank(message = "La dirección del archivo del certificado es obligatoria.")
    private String certUrl;

    /**
     * Constructor por defecto de la clase Certificate.
     */
    public Certificate() {
    }

    /**
     * Constructor que crea una instancia de un certificado con los datos proporcionados.
     * @param description Descripción del certificado.
     * @param date Fecha del certificado.
     * @param imageUrl Dirección url de la ubicación de la imagen del certificado.
     * @param certUrl Dirección url del archivo del certificado.
     */
    public Certificate(String description, String date, String imageUrl, String certUrl) {
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
        this.certUrl = certUrl;
    }

    /**
     * Obtiene el id del certificado.
     * @return El id del certificado.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la descripción del certificado.
     * @return La descripción del certificado.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del certificado.
     * @param description La nueva descripción del certificado.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la fecha del certificado.
     * @return La fecha del certificado.
     */
    public String getDate() {
        return date;
    }

    /**
     * Establece la fecha del certificado.
     * @param date La nueva fecha del certificado.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Obtiene la dirección url de la imagen del certificado.
     * @return La dirección url de la imagen del certificado.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Establece la dirección url de la imagen del certificado.
     * @param imageUrl La nueva dirección url de la imagen del certificado.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Obtiene la dirección url del archivo del certificado.
     * @return La dirección url del archivo del certificado.
     */
    public String getCertUrl() {
        return certUrl;
    }

    /**
     * Establece la dirección url del archivo del certificado.
     * @param certUrl La nueva direccioón del archivo del certificado.
     */
    public void setCertUrl(String certUrl) {
        this.certUrl = certUrl;
    }

    /**
     * Metodo toString que devuelve información sobre el certificado.
     * @return Una cadena de texto con información del certificado.
     */
    @Override
    public String toString() {
        return "Certificate{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
