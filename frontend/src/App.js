import React, { useEffect, useState } from 'react';
import Background from './assets/images/background'
import './styles/app.css';
import Intro from './components/Intro'
import Home from './containers/Home';
import Contact from './containers/Contact'
import BarMenu from './containers/barMenu';
import AboutMe from './containers/AboutMe';

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
