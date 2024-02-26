import React from 'react'
import PropTypes from 'prop-types';
import foto from '../assets/images/avatar.png'
import '../styles/containers/AboutMe.css'

/**
 * Componente AboutMe
 * 
 * Este componente representa la sección "Acerca de mí" de la aplicación.
 * Proporciona información sobre la transición profesional y habilidades del desarrollador.
 */
function AboutMe({ onButtonClick })  {

  /**
   * Maneja el clic en el botón "Contactar".
   * 
   * @param {string} ref - Referencia al componente que se mostrará al hacer clic en "Contactar".
   * @returns {void}
   */
  const handleButtonClick = (ref) => {
    onButtonClick(ref);
  }

  return (
    <div className='aboutMe-container'>
      {/* Contenedor de la foto */}
      <div>
        <img className='foto' src={foto} alt='Retrato'></img>
      </div>

      {/* Contenedor del texto */}
      <div className='container-text'>

        {/* Texto informativo */}
        <div className='text'>
          <p>"Después de trabajar como técnico de mantenimiento en el sector industrial, decidí embarcarme en una 
          emocionante transformación profesional hacia el mundo del desarrollo del software. Con un título en 
          Desarrollo de Aplicaciones Multiplataforma y varios bootcamp, he adquirido habilidades en lenguajes como 
          Java, Python, HTML, CSS o JavaScript, así como en el uso de algunos frameworks como React o Spring. Mi 
          enfoque se basa en la creación de soluciones eficientes, intuitivas y de calidad, combinado con mis 
          habilidades técnicas en el sector industrial. Estoy entusiasmado por continuar creciendo en mi carrera como 
          desarrollador y contribuir al éxito de proyectos desafiantes."
          </p>
        </div>

        {/* Botón para contactar */}
        <button onClick={() => handleButtonClick('contactRef')}>Contactar</button>
      </div>
    </div>
  );
};

AboutMe.propTypes = {
  /**
   * Función para cambiar el componente visible al hacer clic en "Contactar".
   * 
   * @param {string} componentName - Nombre del componente a mostrar.
   * @returns {void}
   */
  onButtonClick: PropTypes.func.isRequired,
};

export default AboutMe;

