import React from 'react';
import PropTypes from 'prop-types';
import '../styles/ProjectCard.css';

/**
 * Componente ProjectCard
 * Representa una tarjeta de proyecto con título, descripción e imagen.
 * @param {string} title - El título del proyecto.
 * @param {string} description - La descripción del proyecto (se acepta HTML).
 * @param {string} imageSrc - La ruta de la imagen del proyecto.
 * @param {function} onClick - Función opcional a ejecutar al hacer clic en la tarjeta.
 * @returns {JSX.Element} - Elemento JSX que representa la tarjeta del proyecto.
 */
const ProjectCard = ({title, description, imageSrc, onClick }) => {

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
      <img src={imageSrc} alt={title} />
      <h2>{title}</h2>
      <p dangerouslySetInnerHTML={{ __html: description }} />
    </div>
  );
};

ProjectCard.propTypes = {
  title: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
  imageSrc: PropTypes.string.isRequired,
  onClick: PropTypes.func,
};

export default ProjectCard;
