import React from 'react';
import JavaImg from '../assets/images/java.jsx'
import SpringImg from '../assets/images/spring.jsx'
import JsImg from '../assets/images/js.jsx'
import ReactImg from '../assets/images/react.jsx'
import HtmlImg from '../assets/images/html.jsx'
import CssImg from '../assets/images/css.jsx'
import GitImg from '../assets/images/git.jsx'
import '../styles/skill.css'
import '../styles/app.css'

const Skill = React.forwardRef((props, ref) => {
    return (
      <div ref={ref} className='section back2'>
        <div className='container-skill'>
          <JavaImg className='skillImage' color='#282c3488'/>
          <SpringImg className='skillImage' color='#282c3488'/>
          <JsImg className='skillImage' color='#282c3488'/>
          <ReactImg className='skillImage' color='#282c3488'/>
          <HtmlImg className='skillImage' color='#282c3488'/>
          <CssImg className='skillImage' color='#282c3488'/>
          <GitImg className='skillImage' color='#282c3488'/>
        </div>
      </div>
    );
  });
  
  export default Skill;
