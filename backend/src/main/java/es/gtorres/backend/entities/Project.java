package es.gtorres.backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Clase que representa un proyecto.
 * Almacena información sobre un proyecto, como título, descripción, tecnologías utilizadas e imágenes.
 */
@Document(collection = "project")
public class Project {

    @Id
    private String id;
    private String title;
    private String description;
    private List<String> skillList;
    private List<String> imagesList;

    /**
     * Constructor por defecto de la clase Project.
     */
    public Project(){
    }

    /**
     * Constructor de la clase Project.
     * Crea una nueva instancia de la clase Project con los parámetros proporcionados.
     * @param title Título del proyecto.
     * @param description Descripción del proyecto.
     * @param skillList Lista de tecnologías empleadas en el proyecto.
     * @param imagesList Lista de imágenes del proyecto.
     */
    public Project(String title, String description, List<String> skillList, List<String> imagesList) {
        this.title = title;
        this.description = description;
        this.skillList = skillList;
        this.imagesList = imagesList;
    }

    /**
     * Obtiene el ID del proyecto.
     * @return El ID del proyecto.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el título del proyecto.
     * @return El título del proyecto.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del proyecto.
     * @param title Título del proyecto.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene la descripción del proyecto.
     * @return La Descripción del proyecto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del proyecto.
     * @param description La descripción del proyecto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene un listado con las tecnologías empleadas en el proyecto.
     * @return Listado de tecnologías.
     */
    public List<String> getSkill() {
        return skillList;
    }

    /**
     * Establece una lista con las tecnologías empleadas en el proyecto.
     * @param skillList Lista de tecnologías.
     */
    public void setSkill(List<String> skillList) {
        this.skillList = skillList;
    }

    /**
     * Obtiene una lista de direcciones de imágenes del proyecto.
     * @return Lista de imágenes.
     */
    public List<String> getImagesList() {
        return imagesList;
    }

    /**
     * Establece una lista de direcciones de imágenes del proyecto.
     * @param imagesList Lista de direcciones de imágenes.
     */
    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    /**
     * Metodo toString que devuelve información sobre el proyecto.
     * @return Una cadena de texto con información del proyecto.
     */
    @Override
    public String toString() {
        return "Project [id=" + id + ", title=" + title + ", description=" + description + ", skills=" + skillList +
                ", images=" + imagesList + "]";
    }
}
