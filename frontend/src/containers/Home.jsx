import React from 'react'
import PropTypes from 'prop-types';
import ImgLinkedin from '../assets/images/linkedin'
import ImgGithub from '../assets/images/github'
import ImgMail from '../assets/images/mail'
import Photo from '../assets/images/foto.jpg'
import '../styles/containers/Home.css'

/**
 * Componente Home
 * 
 * Este componente representa la página de inicio de la aplicación.
 * Proporciona información básica sobre el desarrollador y enlaces a perfiles sociales.
 */
export default function Home({ onButtonClick }) {
  /**
  * Maneja el clic en el botón "Contactar".
  * 
  * @param {string} ref - Referencia al componente que se mostrará al hacer clic en "Contactar".
  * @returns {void}
  */
  const handleButtonClick = (ref) => {
    onButtonClick(ref);
  }

  /**
  * Redirige a la URL especificada en una nueva pestaña.
  * 
  * @param {string} destination - Destino de redirección ('linkedin' o 'github').
  * @returns {void}
  */
  const redirectTo = (destination) => {
    let url;
      switch (destination) {
        case 'linkedin':
          url = 'https://www.linkedin.com/in/juanf-gonzalez-torres/';
          break;
        case 'github':
          url = 'https://github.com/Serra1304';
          break;
        default:
          break;
      }
    window.open(url, '_blank');
  };

  return (
    <div className='home-container'>
      <div className='home-content'>
        <div className='title-container'>
          <h1 className='title-home'>Juan F. Gonzalez</h1>
          <h3 className='sub-title'>Desarrollador de aplicaciones multiplataforma y web Full Stack</h3>
          <div className='icon-container'>
            <div className='icon' onClick={() => redirectTo('linkedin')}><ImgLinkedin /></div>
            <div className='icon' onClick={() => redirectTo('github')}><ImgGithub /></div>
            <div className='icon' onClick={() => handleButtonClick('contactRef')}><ImgMail /></div>
          </div>
          <button onClick={() => handleButtonClick('contactRef')}>Contactar</button>
        </div>
        <img className='photo' src={Photo} alt='Retrato'></img>
      </div>
    </div>
  )
}

Home.propTypes = {
  /**
  * Función para cambiar el componente visible al hacer clic en "Contactar".
  * 
  * @param {string} componentName - Nombre del componente a mostrar.
  * @returns {void}
  */
  onButtonClick: PropTypes.func.isRequired,
};
