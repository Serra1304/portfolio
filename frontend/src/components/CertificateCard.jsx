import React from 'react';
import PropTypes from 'prop-types';
import '../styles/CertificateCard.css';

/**
 * Componente para mostrar una tarjeta de certificado.
 * 
 * Muestra una imagen, descripción y fecha del certificado.
 * Al hacer clic en la tarjeta, se abre el certificado en una nueva pestaña.
 * 
 * @param {object} props - Las props del componente.
 * @param {string} props.imageUrl - URL de la imagen del certificado.
 * @param {string} props.description - Descripción del certificado.
 * @param {string} props.date - Fecha del certificado.
 * @param {string} props.certUrl - URL del certificado en formato PDF.
 * @returns {JSX.Element} Componente de tarjeta de certificado.
 */
const CertificateCard = ({ imageUrl, description, date, certUrl }) => {
    /**
     * Abre el certificado en una nueva pestaña del navegador.
     */
    const pdfViewer = () => {
        window.open(certUrl, '_blank');
    };

    return (
        <div className='certificateCard-container' onClick={pdfViewer}>
            <img src={imageUrl} alt="" className='imageCard' />
            <div className='textCard'>
                <div>{description}</div>
                <div>{date}</div>
            </div>
        </div>
    );
};

CertificateCard.propTypes = {
    imageUrl: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
    certUrl: PropTypes.string.isRequired
  };

export default CertificateCard;