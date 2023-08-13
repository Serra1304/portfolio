import React from 'react';
import foto from '../assets/images/foto.jpg'
import '../styles/aboutMe.css';
import '../styles/app.css'

const AboutMe = React.forwardRef((props, ref) => {

  const scrollToComponent = (targetRef) => {
    targetRef.current.scrollIntoView({ behavior: 'smooth' });
  };

  return (
    <div ref={ref} className='section back1'>
      <div className='container-about'>
        <div>
          <img className='foto' src={foto} alt='Retrato'></img>
          <h1>Juan F. Gonzalez Torres</h1>
          <h3>Desarrollador Full Stack</h3>
        </div>
        <div className='container-text'>
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
          <button onClick={() => scrollToComponent(props.contactRef)}>Contactar</button>
        </div>
      </div>
    </div>
  );
});

export default AboutMe;

