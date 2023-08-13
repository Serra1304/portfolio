import React from 'react';
import '../styles/contact.css'
import '../styles/app.css'
import Formulario from '../form/contactForm'
import Logo from '../assets/images/logo'
import Linkedin from '../assets/images/linkedin'
import Github from '../assets/images/github'

const Contact = React.forwardRef((props, ref) => {

    const redirectTo = (destination) => {
        switch (destination) {
            case 'linkedin':
                window.location.href = 'https://www.linkedin.com/in/juanf-gonzalez-torres/';
                break;
            case 'github':
                window.location.href = 'https://github.com/Serra1304';
                break;
            default:
                break;
        }
    };

    return (
        <div ref={ref} className='section back1 section-contacts'>
            <div className='logo'>
                <Logo className='logo' />
            </div>
                
            <div className='links'>
                <div className='btnLink' onClick={() => redirectTo('linkedin')}>
                    <Linkedin/>
                    Linkedin
                </div>

                <div className='btnLink' onClick={() => redirectTo('github')}>
                    <Github/>
                    Github
                </div>
            </div>
            <Formulario/>
        </div>
    );
});

export default Contact;
