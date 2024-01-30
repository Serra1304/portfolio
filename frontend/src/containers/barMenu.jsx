import React, { useState } from 'react';
// import LogoMinColor from '../assets/images/logoMinColor';
import ImgLogoG from '../assets/images/logo_g'
import ImgMenu from '../assets/images/menu'
import '../styles/menu.css';

function BarMenu({ onButtonClick }) {
  const [positionFixed, setPositionFixed] = useState(false);
  const [isDeployed, setDeployed] = useState(false);
  const menuDeployed = () => {
    setDeployed(!isDeployed);
  }

  const handleButtonClick = (ref) => {
    onButtonClick(ref);

    if (ref === 'homeRef') {
      setPositionFixed(false);
    } else {
      setPositionFixed(true);
    }
  };

  return (
    <div className={`bar ${positionFixed ? 'position-fixed' : ''}`}>
      <div>
        <ImgLogoG className='logo' />
      </div>
      <div className={`bar-menu ${isDeployed?'deployed':''} `}>
        <div className='bar-button from-left' onClick={() => handleButtonClick('homeRef')}>Inicio</div>
        <div className='bar-button from-left' onClick={() => handleButtonClick('aboutMeRef')}>Sobre mi</div>
        <div className='bar-button from-left' onClick={() => handleButtonClick('skillRef')}>Skill</div>
        <div className='bar-button from-left' onClick={() => handleButtonClick('proyectsRef')}>Proyectos</div>
        <div className='bar-button from-left' onClick={() => handleButtonClick('certificatesRef')}>Certificados</div>
        <div className='bar-button from-left' onClick={() => handleButtonClick('contactRef')}>Contacto</div>
      </div>
      <div className='button-menu' onClick={menuDeployed}>
        <ImgMenu className='icon-menu' />
      </div>
    </div>
  );
}

export default BarMenu;