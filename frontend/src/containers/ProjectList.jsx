import React, { useState, useRef, useEffect } from 'react'
import '../styles/proyect.css'
import '../styles/app.css'
import ProjectCard from '../components/ProjectCard'
import ProjectModal from '../components/ProjectModal'
import authToApi from '../service/auth/authService'

const Proyects = React.forwardRef((props, ref) => {

    const apiProjectFindAll = process.env.REACT_APP_API_PROJECT_FINDALL;

    const [projects, setProjects] = useState([]);

    async function fetchProjects() {
        try {
            const token = await authToApi();
            const respons = await fetch(apiProjectFindAll, {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': '*/*',
                    'Content-Type': 'application/json',
                },
            })
        
            if (respons.status === 200) {
                const data = await respons.json();
                setProjects(data);
            } else {
                // Manejar el error si la respuesta no es exitosa
                console.error('Error al obtener los proyectos');
            }
        } catch (error) {
            // Manejar el error de la solicitud
            console.error('Error de red:', error);
        }
    }

    useEffect(() => {
        fetchProjects();
    }, []);

    const [selectedProject, setSelectedProject] = useState(null);

    const openProjectModal = (project) => {
        setSelectedProject(project);
    };

    const closeProjectModal = () => {
        setSelectedProject(null);
    }
    
    return (
        <div ref={ref} className='new-section-proyect back1'>
            <h1 className='new-title'>Proyectos</h1>
            <div className='new-container-project'>
                {projects.map((proyecto) => (
                <ProjectCard
                    key={proyecto.id}
                    title={proyecto.title}
                    description={proyecto.description}
                    imageSrc={proyecto.imagesList[0]}
                    onClick={() => openProjectModal(proyecto)}
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
});

export default Proyects;
