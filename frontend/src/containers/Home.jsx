import React from 'react'
import ImgLinkedin from '../assets/images/linkedin'
import ImgGithub from '../assets/images/github'
import ImgMail from '../assets/images/mail'
import Photo from '../assets/images/foto.jpg'
import '../styles/Home.css'

export default function newHome() {
  return (
    <div className='home-container'>
      <div className='home-content'>
        <div className='title-container'>
          <h1 className='title-home'>Juan F. Gonzalez</h1>
          <h3 className='sub-title'>Desarrollador de aplicaciones multiplataforma y web Full Stack</h3>
          <div className='icon-container'>
            <div className='icon'><ImgLinkedin /></div>
            <div className='icon'><ImgGithub /></div>
            <div className='icon'><ImgMail /></div>
          </div>
          <button>Contactar</button>
        </div>
        <img className='photo' src={Photo} alt='Retrato'></img>
      </div>
    </div>
  )
}
