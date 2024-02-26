import React, { useState } from 'react'
import PropTypes from 'prop-types';
import ProjectCard from '../components/ProjectCard'
import ProjectModal from '../components/ProjectModal'
import '../styles/containers/Project.css'

/**
 * Componente que muestra una lista de proyectos y permite ver más detalles de cada uno en un modal.
 * 
 * @component
 * 
 * @param {object} props - Las props del componente.
 * @param {object[]} props.data - Array de objetos que representan los datos de los proyectos a mostrar.
 * @param {string} props.data[].key - Clave única para cada proyecto.
 * @param {string} props.data[].title - Título del proyecto.
 * @param {string} props.data[].description - Descripción del proyecto.
 * @param {string[]} props.data[].imagesSrc - Array de URL de imagenes del proyecto.
 * 
 * @returns {JSX.Element} Componente de lista de proyectos.
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

            {/* Listado de proyectos. */}
            <div className='project-content'>
                {data.map((project) => (
                    <ProjectCard
                        key={project.key}
                        project={project}
                        onClick={() => openProjectModal(project)}
                    />
                ))}
            </div>

            {/* Muestra ventana modal si se pulsa sobre un proyecto. */}
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
            imagesSrc: PropTypes.arrayOf(PropTypes.object).isRequired,
        })
    ).isRequired,
};

export default Projects;
