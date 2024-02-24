/**
 * Representa un certificado o titulo con su informacion asociada.
 */
export default class Certificate {
    key = '';
    description = '';
    date = '';
    imageUrl = '';
    certUrl = '';

    /**
     * Crea una instancia de Certificate.
     * @param {string} key - La clave única del certificado.
     * @param {string} description - La descripción del certificado.
     * @param {string} date - La fecha del certificado.
     * @param {string} imageUrl - La URL de la imágen del certificado.
     * @param {string} certUrl - La URL del documento del certificado.
     */
    constructor(key, description, date, imageUrl, certUrl) {
        this.key = key;
        this.description = description;
        this.date = date;
        this.imageUrl = imageUrl;
        this.certUrl = certUrl;
    }
}