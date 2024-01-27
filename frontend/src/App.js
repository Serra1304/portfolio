import React, { useEffect, useState } from 'react';
import Background from './assets/images/background'
import './styles/app.css';
import Intro from './components/Intro'
import Home from './containers/oldHome';
import BarMenu from './containers/barMenu';

function App() {
  const [cargaFinalizada, setCargaFinalizada] = useState(false);
  const [introFinished, setIntroFinished] = useState(false);

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

  return (
    <div className='app-container'>
      <Background className='background' />
        {/* {introFinished ? '' : <Intro loadingFinished={cargaFinalizada} introFinished={intro}/>}
        <Home /> */}
      <BarMenu />
      <Home />
    </div>
  );
}

export default App;
