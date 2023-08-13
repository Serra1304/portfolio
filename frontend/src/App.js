import React, { useRef, useEffect } from 'react';
import './styles/menu.css';
import BarMenu from './containers/barMenu';
import AboutMe from './containers/aboutMe';
import Skill from './containers/skill';
import Proyect from './containers/proyectList';
import Certificates from './containers/certificates';
import Contact from './containers/contact';

function App() {
  const aboutMeRef = useRef(null);
  const skillRef = useRef(null);
  const proyectsRef = useRef(null);
  const certificatesRef = useRef(null);
  const contactRef = useRef(null);

  useEffect(() => {
    const scrollToComponent = (ref) => {
      ref?.current?.scrollIntoView({ behavior: 'smooth' });
    };

    window.scrollToComponent = scrollToComponent;

    return () => {
      delete window.scrollToComponent;
    };
  }, []);

  return (
    <div>
        <BarMenu aboutMeRef={aboutMeRef} skillRef={skillRef} proyectsRef={proyectsRef} certificatesRef={certificatesRef} contactRef={contactRef} />
        <AboutMe contactRef={contactRef} ref={aboutMeRef}/>
        <Skill ref={skillRef} />
        <Proyect ref={proyectsRef} />
        <Certificates ref={certificatesRef} />
        <Contact ref={contactRef} />
    </div>
  );
}

export default App;
