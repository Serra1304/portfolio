import React, { useEffect, useState } from 'react';
import Background from './assets/images/background'
import './styles/app.css';
import Intro from './components/Intro'
import BarMenu from './containers/barMenu';
import Home from './containers/Home';
import Skill from './containers/Skill';
import Contact from './containers/Contact';
import AboutMe from './containers/AboutMe';
import Project from './containers/Projects';
import ImageService from './service/assets/PreloadService';

function App() {
  const [cargaFinalizada, setCargaFinalizada] = useState(false);
  const [introFinished, setIntroFinished] = useState(false);
  const [visibleComponent, setVisibleComponent] = useState('homeRef');

  const intro = (introFinished) => {
    setIntroFinished(introFinished);
    document.body.style.overflowY = 'auto';
  }

  useEffect(() => {
    // Simulación de carga de la app
    const tiempoCarga = setTimeout(() => {
      setCargaFinalizada(true); // Indica que la carga ha finalizado
    }, 10000);

    return () => clearTimeout(tiempoCarga);
  }, []);

  // FIXME: Precarga de datos (Seccion en pruebas)
  const[images, setImages] = useState([]);
  const[projects, setProjets] = useState([]);


  //TODO refactorizar fetch.
  useEffect(() => {
    const fetchImages = async () => {
      const imageService = new ImageService();
      setImages(await imageService.loadSkills());
    };

    const fetchProject = async () => {
      const project = new ImageService();
      setProjets(await project.loadProjects());
    };
 
    fetchImages();
    fetchProject();
  }, []);

    // Función para cambiar el componente visible
    const handleButtonClick = (componentName) => {
      setVisibleComponent(componentName);
    };

    // Función para renderizar el componente actualmente visible
    const renderVisibleComponent = () => {
      switch (visibleComponent) {
        case 'homeRef':
          return <Home onButtonClick={handleButtonClick} />;
        case 'aboutMeRef':
          return <AboutMe onButtonClick={handleButtonClick} />
        case 'skillRef':
          return <Skill images={images}/>
        case 'projectRef':
          return <Project data={projects}/>
        case 'contactRef':
          return <Contact />;
        default:
          return null;
      }
    };

  return (
    <div className='app-container'>
      <Background className='background' />
        {/* {introFinished ? '' : <Intro loadingFinished={cargaFinalizada} introFinished={intro}/>}
        <Home /> */}
      <BarMenu onButtonClick={handleButtonClick} />
      {renderVisibleComponent()}
    </div>
  );
}

export default App;
