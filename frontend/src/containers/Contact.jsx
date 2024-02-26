import React from 'react'
import Formulario from '../form/ContactForm'
import Logo from '../assets/images/logo'
import Linkedin from '../assets/images/linkedin'
import Github from '../assets/images/github'
import '../styles/containers/Contact.css'

/**
 * Componente Contact
 * 
 * Este componente representa la sección de contacto de la aplicación.
 * Proporciona enlaces a perfiles de redes sociales y un formulario de contacto.
 * 
 * @returns {JSX.Element} - Elemento JSX que representa la sección de contacto.
 */
export default function Contact() {

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
        <div className='contact-container'>
        
            {/* Contenedor del logo */}
            <div className='logo-contact-container'>
                <Logo className='logo-contact' />
            </div>

            {/* Contenedor de enlaces */}
            <div className='links-container'>
                <div className='button' onClick={() => redirectTo('linkedin')}>
                    <Linkedin />
                    Linkedin
                </div>
                <div className='button' onClick={() => redirectTo('github')}>
                    <Github />
                    Github
                </div>
            </div>

            {/* Componente del formulario de contacto */}
            <Formulario/>
        </div>
    )
}
