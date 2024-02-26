import React, { useEffect, useState } from 'react';
import Background from './assets/images/background'
import Intro from './components/Intro'
import Menu from './components/Menu';
import Home from './containers/Home';
import Skill from './containers/Skill';
import Contact from './containers/Contact';
import AboutMe from './containers/AboutMe';
import Project from './containers/Projects';
import Certificates from './containers/Certificates';
import ImageService from './service/assets/PreloadService';
import './styles/app.css';

/**
 * Componente principal de la aplicación.
 * Este componente es responsable de gestionar el estado global de la aplicación
 * y renderizar los diferentes componentes según sea necesario.
 */
function App() {
  // Estados para almacenar las imágenes, proyectos y certificados cargados.
  const [images, setImages] = useState([]);
  const [projects, setProjets] = useState([]);
  const [certificates, setCertificates] = useState([]);

  const [loadFinished, setLoadFinished] = useState(false);    // Estado para controlar si la carga de la aplicación ha finalizado.
  const [introFinished, setIntroFinished] = useState(false);  // Estado para controlar si la introducción ha finalizado.
  const [visibleComponent, setVisibleComponent] = useState('homeRef');  // Estado para controlar qué componente es visible actualmente.

  /**
   * Función para manejar el evento de finalización de la introducción.
   * @param {boolean} introFinished - Indica si la introducción ha finalizado.
   */
  const intro = (introFinished) => {
    setIntroFinished(introFinished);
    document.body.style.overflowY = 'auto';
  }

  // useEffect para verificar si todos los componentes están cargados.
  useEffect(() => {
    const checkComponentsLoaded = () => {
      if (images.length > 0 && projects.length > 0 && certificates.length > 0) {
        setLoadFinished(true);
      }
    };

    checkComponentsLoaded();
  }, [images, projects, certificates]);

  // useEffect para cargar los componentes de la aplicacion.
  useEffect(() => {
    const fetchImages = async () => {
      const imageService = new ImageService();
      setImages(await imageService.loadSkills());
    };

    const fetchProject = async () => {
      const project = new ImageService();
      setProjets(await project.loadProjects());
    };

    const fetchCertificates = async () => {
      const certificate = new ImageService();
      setCertificates(await certificate.loadCertificates());
    };

    fetchImages();
    fetchProject();
    fetchCertificates();
  }, []);

  /**
   * Función para cambiar el componente visible.
   * @param {string} componentName - El nombre del componente a mostrar.
   */
  const handleButtonClick = (componentName) => {
    setVisibleComponent(componentName);
  };

  /**
   * Función para renderizar el componente actualmente visible.
   * @returns {JSX.Element|null} El componente actualmente visible.
   */
  const renderVisibleComponent = () => {
    switch (visibleComponent) {
      case 'homeRef':
        return <Home onButtonClick={handleButtonClick} />;
      case 'aboutMeRef':
        return <AboutMe onButtonClick={handleButtonClick} />
      case 'skillRef':
        return <Skill images={images} />
      case 'projectRef':
        return <Project data={projects} />
      case 'certificatesRef':
        return <Certificates data={certificates} />
      case 'contactRef':
        return <Contact />;
      default:
        return null;
    }
  };

  return (
    <div className='app-container'>
      <Background className='background' />
      <Menu onButtonClick={handleButtonClick} />
      
      {/* Renderizar la introducción mientras la carga de componentes no haya finalizado. */}
      {introFinished ? '' : <Intro loadingFinished={loadFinished} introFinished={intro} />}

      {/* Renderiza el componente actualmente visible. */}
      {renderVisibleComponent()}
    </div>
  );
}

export default App;
