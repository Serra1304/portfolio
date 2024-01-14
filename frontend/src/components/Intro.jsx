import React, { useState, useEffect } from 'react'
import ImgLogoG from '../assets/images/logo_g'
import ImgLogoDivisor from '../assets/images/logo_divisor'
import ImgLogoGtorres from '../assets/images/logo_gtorres'
import ImgLogoDeveloper from '../assets/images/logo_developer'
import '../styles/Intro.css'


/**
 * Componente de animación de introducción que muestra un logotipo y una vez a terminado la carga
 * de la pagina, lo oculta.
 *
 * @component
 * @param {Object} props - Propiedades del componente.
 * @param {Function} props.loadingFinished - Función que indica si la carga de la página ha finalizado.
 * @param {Function} props.introFinished - Función que indica si el despliegue de la intro a finalizado.
 */
function IntroAnimation({ loadingFinished, introFinished }) {

    // Estados para controlar la visibilidad y animacion del logotipo
    const [showLogo, setShowLogo] = useState(false);
    const [showLogoOut, setShowLogoOut] = useState(false);
    const [showLogoFinished, setShowLogoFinished] = useState(false);

    // Duraciones y tiempos de espera para las animaciones.
    const introDelay = 2000;
    const logoShowInDuration = 10000;
    const logoShowOutDuration = 4000;

    /**
     * Despliega la intro pasado 2 segundos y le indica a la app que termina a los 10 segundos de
     * comenzar. Estos tiempos se ajustan segun el retardo de inicio deseado y el tiempo que tarda
     * la intro en desplegarse.
     */
    useEffect(() => {
        const delayShowLogoOut = setTimeout(() => {
            setShowLogo(true);

            const delayShowLogo = setTimeout(() => {
                setShowLogoFinished(true);
            },logoShowInDuration);
            return () => clearTimeout(delayShowLogo);
        }, introDelay)        
        return () => clearTimeout(delayShowLogoOut);
    }, []);


    /**
     * Comprueba que el intro se ha desplegado y la carga de la pagina esta finalizada,
     * antes de proceder a ocultar la intro.
     */
    useEffect(() => {
      if (loadingFinished && showLogoFinished) {
        setShowLogoOut(true);
        introFinish();
      }
    }, [loadingFinished, showLogoFinished]);

  
    /**
     * Le indica a la aplicacion que la intro a terminado a los 4 segundos de empezar a ocultarla.
     * Este tiempo se ajusta en funcion de lo que tarda el logo en ocultarse.
     */
    const introFinish = () => {
        const intro = setTimeout(() => {
            introFinished(true);
        }, logoShowOutDuration);
        return () => clearTimeout(intro);
    }


    return (
        <div className={`container-intro ${showLogoOut ? 'out-intro3' : ''}`} >
            <div className={`${showLogoOut ? 'out-intro1' : ''}`}>
                {showLogo && <div className='container-logo'>
                    <div className='display-logoG'><ImgLogoG className='logoG' /></div>
                    <div className={`container-logo ${showLogoOut ? 'out-intro2' : ''}`}>
                        <div className='display-logoDivisor'><ImgLogoDivisor className='logoDivisor'/></div>
                        <div className='subcontainer-logo'>
                            <div className='display-logoGtorres'><ImgLogoGtorres className='logoGtorres'/></div>
                            <div className='display-logoDeveloper'><ImgLogoDeveloper className='logoDeveloper'/></div>
                       </div>
                   </div>
               </div>}
            </div>
        </div>
    )
}

export default IntroAnimation;
