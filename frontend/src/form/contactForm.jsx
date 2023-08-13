import React from 'react';
import { Formik, Field, Form } from 'formik'
import axios from 'axios';

import '../styles/contact.css'
import '../styles/app.css'

const ContactForm = () => {

const contactInformation = {
    name: '',
    company: '',
    email: '',
    description: '',
}

    return (
        <div className='form'>
            <Formik 
            initialValues= { contactInformation }
            onSubmit={async (values, { resetForm }) =>{
                    try {
                        await axios.post(process.env.REACT_APP_API, values);
                        resetForm();
                    } catch (error) {
                        alert('Ha ocurrido un error al enviar el formulario.');
                        resetForm();
                    }
                }}>

                <Form>
                    <label htmlFor='name'>Nombre:</label>
                    <Field className='field' id='name' name='name' placeholder='Nombre' required></Field>

                    <label htmlFor='company'>Empresa:</label>
                    <Field className='field' id='company' name='company' placeholder='Empresa' required></Field>

                    <label htmlFor='email'>Email:</label>
                    <Field className='field' id='email' name='email' placeholder='Email' required></Field>

                    <label htmlFor='description'>Descripcion:</label>
                    <Field className='area' as='textarea' id='description' name='description' placeholder='Descripcion' required></Field>

                    <button type="submit" className=''>Enviar</button>
                </Form>

            </Formik>
        </div>
    );
}

export default ContactForm;
