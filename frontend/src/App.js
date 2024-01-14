import React, { useEffect, useState } from 'react';
import './styles/app.css';
import Intro from './components/Intro'
import Home from './containers/Home';

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
    <div>
        {introFinished ? '' : <Intro loadingFinished={cargaFinalizada} introFinished={intro}/>}
        <Home />
    </div>
  );
}

export default App;
