import React from 'react';
import PropTypes from 'prop-types';
import '../styles/components/ProjectCard.css';

/**
 * Componente para representar visualmente un proyecto en una tarjeta.
 * 
 * Muestra una imagen, título y descripción del proyecto.
 * 
 * @component
 * 
 * @param {object} project - Objeto que representa los datos del proyecto.
 * @param {string} project.title - Título del proyecto.
 * @param {string} project.description - Descripción del proyecto (puede contener HTML).
 * @param {object[]} project.imagesSrc - Array de objetos que contienen información de las imágenes del proyecto.
 * @param {function} onClick - Función que se ejecuta cuando se hace clic en la tarjeta del proyecto.
 * 
 * @returns {JSX.Element} Componente de tarjeta de proyecto.
 */
const ProjectCard = ({project, onClick }) => {

  /**
   * Maneja el click en la tarjeta del proyecto y ejecuta la función proporcionada (si existe).
   */
  const handleClick = () => {
    if (onClick) {
      onClick();
    }
  };

  return (
    <div className="project-card" data-testid="project-card" onClick={handleClick}>
      <img src={project.imagesSrc[0].src} alt={project.title} />
      <h2>{project.title}</h2>
      <p dangerouslySetInnerHTML={{ __html: project.description }} />
    </div>
  );
};

ProjectCard.propTypes = {
  project: PropTypes.shape({
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    imagesSrc: PropTypes.arrayOf(
      PropTypes.shape({
        src: PropTypes.string.isRequired
      })
    ).isRequired
  }).isRequired,
  onClick: PropTypes.func,
};

export default ProjectCard;
