import React, { useState, useRef } from 'react';
import '../styles/proyect.css'
import '../styles/app.css'
import Proyecto from './proyect'
import { Proyect } from '../models/proyect.class'
import ProyectImg from '../assets/images/proyect-portfolio.png'
import MongodbImg from '../assets/images/mongodb'
import JavaImg from '../assets/images/java'
import SpringImg from '../assets/images/spring'
import ReactImg from '../assets/images/react'
import JsImg from '../assets/images/js'
import HtmlImg from '../assets/images/html'
import CssImg from '../assets/images/css'

const Proyects = React.forwardRef((props, ref) => {
    const [selectedProyect, setSelectedProyect] = useState(null);
    const [indexSelected, setIndexSelected] = useState(0);
    const proyectoRef = useRef();

    const handleProyectSelection = (index) => {
        const selected = proyectos[index];
        setIndexSelected(index)
        setSelectedProyect(selected);
        proyectoRef.current.setIsReplegado(false);
    }

    const proyectos = [];

    const proyect1 = new Proyect(
        'Portfolio', 
        [<img className='image-proyect' src={ProyectImg} alt='' />], 
        [<MongodbImg className='image-tech' color='#282c34' />, 
        <SpringImg className='image-tech' color='#282c34' />, 
        <JavaImg className='image-tech' color='#282c34' />, 
        <ReactImg className='image-tech' color='#282c34' />, 
        <JsImg className='image-tech' color='#282c34' />, 
        <HtmlImg className='image-tech' color='#282c34' />, 
        <CssImg className='image-tech' color='#282c34' />]);
    proyectos.push(proyect1);
    
    return (
        <div ref={ref} className='section back1'>
            <div className='container-proyect'>
                <div className='section-proyect'>
                    <Proyecto proyect={selectedProyect || proyect1} ref={proyectoRef}></Proyecto>
                </div>
                <div className='section-selectProyect'>
                {proyectos.map((proyecto, index) => {
                    return (
                    <div className='selection' key={index} onClick={() => handleProyectSelection(index)}>
                    <h3 className={`${indexSelected === index  ? 'selected' : ''}`}>{index + 1}</h3>
                    </div>);
                })}
                </div>
            </div>
        </div>
    );
});

export default Proyects;
