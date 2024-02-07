import authToApi from '../auth/authService'

/**
 * Servicio para cargar imágenes desde una API y pre-cargarlas en la memoria del navegador.
 */
class ImageLoadService {

    /**
     * Obtiene las habilidades desde la API y pre-carga las imágenes asociadas.
     * @returns {Promise<HTMLImageElement[]>} Un array de objetos de imagen HTML pre-cargados.
     */
    async fetchSkills() {
        try {
            // Obtiene la URL de la API de habilidades desde las variables de entorno.
            const apiSkillFindAll = process.env.REACT_APP_API_SKILL_FINDALL;

            // Obtiene el token de autenticación para acceder a la API.
            const token = await authToApi();

            // Realiza una solicitud a la API para obtener las habilidades.
            const response = await fetch(apiSkillFindAll, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
            });
    
            // Verifica si la respuesta es exitosa.
            if (response.status === 200) {
                // Convierte la respuesta a formato JSON.
                const data = await response.json();
                
                // Pre-carga las imágenes asociadas a las habilidades.
                const loadedImages = this.preloadImages(data);
                return loadedImages;
            } else {
                // Registra un error si la solicitud no es exitosa.
                console.error('Error al obtener los proyectos');
            }
        } catch (error) {
            // Maneja los errores de red.
            console.error('Error de red:', error);
        }
    }
    
    /**
     * Pre-carga las imágenes en la memoria del navegador.
     * @param {Object[]} data - Un array de objetos que contienen información de las imágenes.
     * @returns {HTMLImageElement[]} Un array de objetos de imagen HTML pre-cargados.
     */
    preloadImages(data) {
        const loadedImages = [];
    
        // Itera sobre los datos de las imágenes y carga cada una de ellas.
        data.forEach(image => {
            if (image.url) {
                const img = new Image();
                img.src = image.url;
                loadedImages.push(img);
            }
        });
        return loadedImages;
    }    
}
  
export default ImageLoadService;
