import React, { useState } from 'react'
import PropTypes from 'prop-types';
import ProjectCard from '../components/ProjectCard'
import ProjectModal from '../components/ProjectModal'
import '../styles/Project.css'

/**
 * Componente para mostrar una lista de proyectos y un modal detallado de cada proyecto seleccionado.
 * @param {Object[]} data - La lista de proyectos a mostrar.
 * @param {string} data[].key - La clave única de cada proyecto.
 * @param {string} data[].title - El título de cada proyecto.
 * @param {string} data[].description - La descripción de cada proyecto.
 * @param {string[]} data[].imagesSrc - Las URLs de las imágenes asociadas a cada proyecto.
 * @returns {JSX.Element} El componente Projects.
 */
function Projects({ data }) {

    /**
     * Estado para almacenar el proyecto seleccionado que se mostrara en el modal.
     */
    const [selectedProject, setSelectedProject] = useState(null);

    /**
     * Función para abrir el modal con el proyecto seleccionado.
     * @param {Object} project - El proyecto seleccionado.
     */
    const openProjectModal = (project) => {
        setSelectedProject(project);
    };

    /**
     * Función para cerrar el modal.
     */
    const closeProjectModal = () => {
        setSelectedProject(null);
    }

    return (
        <div className='project-container'>
            <div className='project-content'>
                {data.map((project) => (
                    <ProjectCard
                        key={project.key}
                        title={project.title}
                        description={project.description}
                        image={project.imagesSrc[0]}
                        onClick={() => openProjectModal(project)}
                    />
                ))}
            </div>
            {selectedProject && (
                <ProjectModal
                    project={selectedProject}
                    onClose={closeProjectModal}
                />
            )}
        </div>
    );
}

Projects.propTypes = {
    data: PropTypes.arrayOf(
        PropTypes.shape({
            key: PropTypes.string.isRequired,
            title: PropTypes.string.isRequired,
            description: PropTypes.string.isRequired,
            imagesSrc: PropTypes.arrayOf(PropTypes.string).isRequired,
        })
    ).isRequired,
};

export default Projects;
