import React from 'react';
import PropTypes from 'prop-types'
import CertificateCard from '../components/CertificateCard';
import '../styles/Certificates.css'

/**
 * Componente para mostrar una lista de certificados.
 * 
 * Renderiza una lista de tarjetas de certificados utilizando el componente CertificateCard.
 * 
 * @param {object} props - Las props del componente.
 * @param {array} props.data - Array de objetos que representan los datos de los certificados a mostrar.
 * @param {string} props.data[].key - Clave única para cada certificado.
 * @param {string} props.data[].imageUrl - URL de la imagen del certificado.
 * @param {string} props.data[].description - Descripción del certificado.
 * @param {string} props.data[].date - Fecha del certificado.
 * @param {string} props.data[].certUrl - URL del certificado en formato PDF.
 * @returns {JSX.Element} Componente de lista de certificados.
 */
function Certificates({ data }) {

    return (
        <div className='certificates-container' >
            <div className='certificates-content'>
                {data.map((certificate) => (
                    <CertificateCard
                        key={certificate.key}
                        imageUrl={certificate.imageUrl}
                        description={certificate.description}
                        date={certificate.date}
                        certUrl={certificate.certUrl}
                    />
                ))}
            </div>
        </div>
    );
}

Certificates.propTypes = {
    data: PropTypes.arrayOf(PropTypes.shape({
        key: PropTypes.string.isRequired,
        imageUrl: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        date: PropTypes.string.isRequired,
        certUrl: PropTypes.string.isRequired,
    })).isRequired,
};

export default Certificates;
