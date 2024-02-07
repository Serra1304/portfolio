import React from 'react';
import PropTypes from 'prop-types';
import '../styles/Skill.css'


/**
 * Componente Skill para mostrar una colección de imágenes de habilidades.
 * @param {Object} props - Las props del componente.
 * @param {Object[]} props.images - Un array de objetos que representan las imágenes de habilidades.
 * @param {string} props.images[].src - La URL de la imagen de la habilidad.
 * @param {string} [props.images[].id] - El identificador único de la imagen de la habilidad (opcional).
 * @returns {JSX.Element} Componente Skill.
 */
function Skill({ images }) {

    return (
      <div className='skill-container'>
        <div className='skill-content'>
        {images.map((image, index) => (
          <img key={index} className='skill-image' src={image.src} alt='' />
        ))}
        </div>
      </div>
    );
  };

/**
 * Definición de tipos de las props del componente Skill.
 */
Skill.propTypes = {
  images: PropTypes.arrayOf(
      PropTypes.shape({
          src: PropTypes.string.isRequired,
      })
  ).isRequired
};
  
  export default Skill;
