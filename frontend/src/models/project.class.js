/**
 * Representa un proyecto con su informacion asociada.
 */
export default class Project {
    key = '';
    title = '';
    description = '';
    imagesSrc = [];
    skills = [];

    /**
     * Crea una instancia de Project.
     * @param {string} key - La clave única del proyecto.
     * @param {string} title - El título del proyecto.
     * @param {string} description - La descripción del proyecto.
     * @param {string[]} imagesSrc - Las URLs de las imágenes asociadas al proyecto.
     * @param {string[]} skills - Las habilidades asociadas al proyecto.
     */
    constructor(key, title, description, imagesSrc, skills) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.imagesSrc = imagesSrc;
        this.skills = skills;
    }
}