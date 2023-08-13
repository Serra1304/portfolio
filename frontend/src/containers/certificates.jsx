import React from 'react';
import '../styles/certificates.css'
import '../styles/app.css'
import Cards from './cards';
import Dam from '../assets/images/dam'
import CerDam from '../assets/docs/CER_Dam.pdf'
import Linux from '../assets/images/linux'
import CerLinux from '../assets/docs/CER_Linux.pdf'
import Java from '../assets/images/java'
import CerJava from '../assets/docs/CER_Java.pdf'
import Js from '../assets/images/js'
import CerJs from '../assets/docs/CER_Js.pdf'
import HtmlCss from '../assets/images/htmlCss'
import CerHtmlCss from '../assets/docs/CER_HtmlCss.pdf'
import Git from '../assets/images/git'
import CerGit from '../assets/docs/CER_Git.pdf'

const Certificates = React.forwardRef((props, ref) => {

    return (
        <div ref={ref} className='section back2 container-certificates'>
            <Cards 
                svg={<Dam color='#ff5c00' />} 
                title={'Tecnico superior en desarrollo de aplicaciones multiplataforma'} 
                age={'sep 2021 - jun 2023'}
                url={CerDam}
                />
            <Cards 
                svg={<Linux color='#ff5c00' />} 
                title={'IFC066PO Linux profesional avanzado'} 
                age={'feb 2022 - abr 2022'} 
                url={CerLinux}
                />
            <Cards 
                svg={<Java color='#ff5c00' />} 
                title={'OpenBootcamp Certificado Java avanzado'} 
                age={'jul 2023'} 
                url={CerJava}
                />
            <Cards 
                svg={<Js color='#ff5c00' />} 
                title={'OpenBootcamp Certificado JavaScript'} 
                age={'ago 2022'}
                url={CerJs}
                />
            <Cards 
                svg={<HtmlCss color='#ff5c00' />} 
                title={'OpenBootcamp Certificado Html y Css'} 
                age={'jul 2022'}
                url={CerHtmlCss}
                />
            <Cards 
                svg={<Git color='#ff5c00' />} 
                title={'OpenBootcamp Certificado Git'} 
                age={'may 2023'}
                url={CerGit}
                />
        </div>
    );
});

export default Certificates;
