import authToApi from '../auth/authService.js'
import Project from '../../models/project.class.js';

/**
 * Servicio para pre-cargar los datos de los distintos componentes en la memoria del navegador.
 */
class PreloadService {

    /**
     * Obtiene las habilidades desde la API y pre-carga las imágenes asociadas.
     * @returns {Promise<HTMLImageElement[]>} Un array de objetos de imagen HTML pre-cargados.
     */
    async loadSkills() {
        const apiSkillFindAll = process.env.REACT_APP_API_SKILL_FINDALL;
        const data = await this.fetchData(apiSkillFindAll);

        if (Array.isArray(data)) {
            const images = [];
            let loadedImages = [];
          
            data.forEach(image => {
                images.push(image.url)
            });
            loadedImages = this.loadImages(images);

            return loadedImages;
        }
        return [];
    }

    /**
     * Obtiene los proyectos desde la API y pre-carga las imágenes asociadas.
     * @returns {Promise<Project[]>} Un array de instancias de proyectos pre-cargados.
     */
    async loadProjects() {
        const apiProjectFindAll = process.env.REACT_APP_API_PROJECT_FINDALL;
        const data = await this.fetchData(apiProjectFindAll);

        if (Array.isArray(data)) {
            const loadedProjects = [];

            for (const projectData of data) {
                const project = new Project(
                    projectData.id,
                    projectData.title,
                    projectData.description,
                    this.loadImages(projectData.imagesList),
                    projectData.skill
                )
                loadedProjects.push(project);
            }
            return loadedProjects;
        }
        return [];
    }

    /**
     * Realiza una solicitud a la API y maneja la autenticación.
     * @param {string} endPoint - El punto final de la API al que se realizará la solicitud.
     * @returns {Promise<any>} Los datos obtenidos de la API.
     */
    async fetchData(endPoint) {
        try {
            // Obtiene el token de autenticación para acceder a la API.
            const token = await authToApi();

            // Realiza una solicitud al endPoint con el token de acceso.
            const response = await fetch(endPoint, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
            });

            // Verifica si la respuesta es exitosa.
            if (response.status === 200) {
                // Convierte la respuesta a formato JSON y retorna los datos.
                return await response.json();
            } else {
                // Registra un error si la solicitud no es exitosa.
                console.error('Error al obtener las imagenes');
            }
        } catch (error) {
            // Maneja los errores de red.
            console.error('Error de red:', error);
        }
    }

    /**
     * Carga las imágenes de una lista y las devuelve como objetos de imagen HTML.
     * @param {string[]} images - Lista de URLs de imágenes.
     * @returns {HTMLImageElement[]} Array de objetos de imagen HTML.
     */
    loadImages(images) {
        const loadedImages = [];

        images.forEach(image => {
            const img = new Image();
            img.src = image;
            loadedImages.push(img);
        });
        return loadedImages;
    }
}

export default PreloadService;
