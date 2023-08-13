import React from 'react';
import '../styles/certificates.css'

const Cards = ({ svg, title, age, url }) => {
  const pdfViewer = () => {
    window.open(url, '_blank');
  };

    return (
        <div className='containerCard' onClick={pdfViewer}>
            <div className='imageCard'>{svg}</div>
            <div className='textCard'>
                <div>{title}</div>
                <div>{age}</div>
            </div>
        </div>
    );
};


export default Cards;
