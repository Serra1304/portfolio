package es.gtorres.backend.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Clase que representa una solicitud de contacto.
 * Almacena información sobre la solicitud de contacto, incluyendo nombre, empresa, email y descripción.
 */
@Document(collection = "contact_requests")
public class ContactRequest {

    @Id private String id;
    @CreatedDate private LocalDateTime date;
    private String name;
    private String company;
    private String email;
    private String description;

    /**
     * Constructor por defecto de la clase ContactRequest.
     */
    public ContactRequest() {
    }

    /**
     * Constructor de la clase ContactRequest.
     * Crea una nueva instancia de ContactRequest con los parámetros proporcionados.
     * @param name Nombre del solicitante.
     * @param company Empresa del solicitante.
     * @param email Email de contacto.
     * @param description Descripción detallada de la solicitud.
     */
    public ContactRequest(String name, String company, String email, String description) {

        this.name = name;
        this.company = company;
        this.email = email;
        this.description = description;
    }

    /**
     * Obtiene el ID de la solicitud de contacto.
     * @return ID de la solicitud de contacto.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la fecha de la solicitud de contacto.
     * @return Fecha de la solicitud de contacto.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Obtiene el nombre del solicitante.
     * @return Nombre del solicitante.
     */
    public String getName() {
        return name;
    }

    /**
     * Estable el nombre del solicitante.
     * @param name Nombre del solicitante.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la empresa de la solicitud.
     * @return La empresa de la solicitud.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Establece la empresa de la solicitud.
     * @param company La empresa de la solicitud.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Obtiene el email de la solicitud.
     * @return El email de la solicitud.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email de la solicitud.
     * @param email El email de la solicitud.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la descripción de la solicitud.
     * @return La descripción de la solicitud.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la solicitud.
     * @param description La descripción de la solicitud.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
