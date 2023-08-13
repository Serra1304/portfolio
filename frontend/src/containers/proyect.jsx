import React, { useState, useEffect, useImperativeHandle, useRef } from 'react';
import '../styles/proyect.css'
import PropTypes from 'prop-types';
import { Proyect } from '../models/proyect.class'

const ProyectComponent  = React.forwardRef((props, ref) => {
    const [isReplegado, setIsReplegado] = useState(false);
    const [showImages, setShowImages] = useState(false);
    const timerRef = useRef(null);
    const isSmallScreen = window.innerWidth < 1023;

    useEffect(() => {
      timerRef.current = setTimeout(() => {
        setIsReplegado(true);
      }, 5000);
  
      return () => {
        clearTimeout(timerRef.current);
      };
    }, [props.proyect]);
  
    const handleMouseEnter = () => {
        clearTimeout(timerRef.current);
        setIsReplegado(false);
      
    };
  
    const handleMouseLeave = () => {
        clearTimeout(timerRef.current);
        timerRef.current = setTimeout(() => {
          setIsReplegado(true);
        }, 5000);
    };

    useEffect(() => {
        let imagesTimer;

        if (!isReplegado) {
          imagesTimer = setTimeout(() => {
            setShowImages(true);
          }, 1000);
        } else {
          setShowImages(false);
        }
    
        return () => {
          clearTimeout(imagesTimer);
        };
      }, [isReplegado]);

      useImperativeHandle(ref, () => ({
        setIsReplegado
      }));

    return (
        <div>
            <div className='title pp'>
                <h1>{ props.proyect.title }</h1>
            </div>
            <div>
              {props.proyect.imgProyect.map((image, index) => (
                <div key={index}> {image} </div>))}
            </div>
            <div className={`tech ${isReplegado ? 'replegado' : ''}`}
                onMouseEnter={handleMouseEnter}
                onMouseLeave={handleMouseLeave}
                >
                {isSmallScreen ? (
                  props.proyect.imgTech.map((image, index) => (
                    <div className='image-list' key={index}> {image} </div>
                  ))
                ) : (
                  showImages && props.proyect.imgTech.map((image, index) => (
                    <div className='image-list' key={index}> {image} </div>
                  ))
                )}
            </div>
        </div>
    );
});


ProyectComponent.propTypes = {
    proyect: PropTypes.instanceOf(Proyect)
};


export default ProyectComponent;
