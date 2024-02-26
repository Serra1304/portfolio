import React from 'react';
import PropTypes from 'prop-types';
import '../styles/components/CertificateCard.css';

/**
 * Componente para mostrar visualmente un certificado en una tarjeta.
 * 
 * Muestra una imagen, descripción y fecha del certificado, y permite abrir el 
 * certificado en una nueva pestaña del navegador al hacer clic en la tarjeta.
 * 
 * @component
 * 
 * @param {object} certificate - Objeto que representa los datos del certificado.
 * @param {string} certificate.imageUrl - URL de la imagen del certificado.
 * @param {string} certificate.description - Descripción del certificado.
 * @param {string} certificate.date - Fecha del certificado.
 * @param {string} certificate.certUrl - URL del certificado en formato PDF.
 * 
 * @returns {JSX.Element} Componente de tarjeta de certificado.
 */
const CertificateCard = ({ certificate }) => {
    /**
     * Abre el certificado en una nueva pestaña del navegador.
     */
    const pdfViewer = () => {
        window.open(certificate.certUrl, '_blank');
    };

    return (
        <div className='certificateCard-container' onClick={pdfViewer}>
            <img src={certificate.imageUrl} alt="" className='imageCard' />
            <div className='textCard'>
                <div>{certificate.description}</div>
                <div>{certificate.date}</div>
            </div>
        </div>
    );
};

CertificateCard.propTypes = {
    certificate: PropTypes.shape({
        imageUrl: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        date: PropTypes.string.isRequired,
        certUrl: PropTypes.string.isRequired,
    }).isRequired,
};

export default CertificateCard;