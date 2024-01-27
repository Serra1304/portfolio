import React, { useState } from 'react';
// import LogoMinColor from '../assets/images/logoMinColor';
import ImgLogoG from '../assets/images/logo_g'
import ImgMenu from '../assets/images/menu'
import '../styles/menu.css';

function BarMenu({ homeRef, aboutMeRef, skillRef, proyectsRef, certificatesRef, contactRef }) {
  const scrollToComponent = (ref) => {
    ref?.current?.scrollIntoView({ behavior: 'smooth' });
  };
  
  const [isDeployed, setDeployed] = useState(false);
  const menuDeployed = () => {
    setDeployed(!isDeployed);
  }

  return (
    <div className='bar'>
      <div>
        <ImgLogoG className='logo' />
      </div>
      <div className={`bar-menu ${isDeployed?'deployed':''}`}>
        <div className='bar-button from-left' onClick={() => scrollToComponent(homeRef)}>Inicio</div>
        <div className='bar-button from-left' onClick={() => scrollToComponent(aboutMeRef)}>Sobre mi</div>
        <div className='bar-button from-left' onClick={() => scrollToComponent(skillRef)}>Skill</div>
        <div className='bar-button from-left' onClick={() => scrollToComponent(proyectsRef)}>Proyectos</div>
        <div className='bar-button from-left' onClick={() => scrollToComponent(certificatesRef)}>Certificados</div>
        <div className='bar-button from-left' onClick={() => scrollToComponent(contactRef)}>Contacto</div>
      </div>
      <div className='button-menu' onClick={menuDeployed}>
        <ImgMenu className='icon-menu' />
      </div>
    </div>
  );
}

export default BarMenu;