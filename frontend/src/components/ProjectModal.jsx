import React from 'react';
import PropTypes from 'prop-types';
import '../styles/ProjectModal.css';

/**
 * Componente de ventana modal para mostrar detalles de un proyecto.
 * @param {Object} props - Las propiedades del componente.
 * @param {Object} props.project - Los detalles del proyecto a mostrar.
 * @param {string} props.project.title - El título del proyecto.
 * @param {string} props.project.imagesList - La URL de la imagen del proyecto.
 * @param {string[]} props.project.skill - Lista de habilidades utilizadas en el proyecto.
 * @param {string} props.project.description - La descripción del proyecto.
 * @param {Function} props.onClose - Función a llamar al cerrar la ventana modal.
 * @returns {JSX.Element} - JSX que representa la ventana modal del proyecto.
 */
const ProjectModal = ({ project, onClose }) => {
    /**
     * Detiene la propagación del evento click para evitar que se cierre la ventana si se hace click dentro de los detalles del proyecto.
     * @param {Object} e - Evento click.
     */
    const stopPropagation = (e) => {
        e.stopPropagation();
    }

    return (
        <div className='modal-page' onClick={onClose}>
            <div className='modal-container back1' onClick={stopPropagation}>
                <div className='modal-title'>
                    <h2>{project.title}</h2>
                </div>
                <div className='modal-body'>
                    <div className='modal-image'>
                        <img src={project.imagesList[0]} alt={project.title} />
                    </div>
                    <div className='modal-skill'>
                        <ul>
                            {project.skill.map((skill, index) => (
                                <li key={index}>{skill}</li>
                            ))}
                        </ul>    
                    </div>
                </div>
                <div className='modal-description'>
                    <p dangerouslySetInnerHTML={{ __html: project.description }} />
                </div>
            </div>
        </div>
    );
};

ProjectModal.propTypes = {
    project: PropTypes.shape({
        title: PropTypes.string.isRequired,
        imagesList: PropTypes.arrayOf(PropTypes.string).isRequired,
        skill: PropTypes.arrayOf(PropTypes.string).isRequired,
        description: PropTypes.string.isRequired,
    }).isRequired,

    onClose: PropTypes.func.isRequired,
};

export default ProjectModal;
