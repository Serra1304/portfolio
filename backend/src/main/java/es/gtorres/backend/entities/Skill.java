package es.gtorres.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa ana habilidad.
 * Almacena información sobre una habilidad, como id, nombre y ruta de una imagen.
 */
@Document(collection = "skills")
public class Skill {

    @Id
    private String id;
    private String name;
    private String url;

    /**
     * Constructor por defecto
     */
    public Skill() {
    }

    /**
     * Constructor que instancia un objeto de la clase skill con los parámetros proporcionados.
     * @param name Nombre de la habilidad.
     * @param url Dirección url de la imagen asociada.
     */
    public Skill(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * Obtiene el id de la habilidad.
     * @return El id de la habilidad.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id de la habilidad.
     * @param id El nuevo id de la habilidad.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la habilidad.
     * @return El nombre de la habilidad.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la habilidad.
     * @param name En nombre nuevo de la habilidad.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la dirección url de la imagen asociada a la habilidad.
     * @return La dirección url de la imagen asociada a la habilidad.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la dirección url de la imagen asociada a la habilidad.
     * @param url La nueva dirección url de la imagen asociada a la habilidad.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Metodo toString que devuelve información sobre la habilidad.
     * @return Una cadena de texto con información de la habilidad.
     */
    @Override
    public String toString() {
        return "skill{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
