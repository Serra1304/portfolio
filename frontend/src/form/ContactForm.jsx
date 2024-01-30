import React from 'react';
import { Formik, Field, Form } from 'formik'
import axios from 'axios';
import '../styles/ContactForm.css'

/**
 * Componente ContactForm
 * 
 * Este componente representa un formulario de contacto.
 * Utiliza la biblioteca Formik para la gestión del estado del formulario.
 * 
 * Propiedades:
 * No acepta propiedades externas.
 * 
 * @returns {JSX.Element} - Elemento JSX que renderiza el formulario de contacto.
 */
const ContactForm = () => {
    // Estado inicial del formulario
    const contactInformation = {
        name: '',
        company: '',
        email: '',
        description: '',
    }

    /**
     * Manejador de envío de formulario
     * 
     * @param {Object} values - Valores del formulario.
     * @param {Object} formActions - Acciones del formulario proporcionadas por Formik.
     * @returns {void}
     */
    const handleSubmit = async (values, { resetForm }) => {
        try {
            // Envía los datos del formulario al servidor y reinicia el formulario
            await axios.post(process.env.REACT_APP_API, values);
            resetForm();
        } catch (error) {
            // Muestra una alerta en caso de error en el envío del formulario y reinicia el formulario.
            alert('Ha ocurrido un error al enviar el formulario.');
            resetForm();
        }
    };

    return (
        <div className='form-container'>
            {/* Utiliza Formik para manejar el estado y la validación del formulario */}
            <Formik 
                initialValues= { contactInformation }
                onSubmit={handleSubmit}
            >
                <Form>
                    {/* Campos de entrada del formulario */}
                    <label htmlFor='name'>Nombre:</label>
                    <Field className='field' id='name' name='name' placeholder='Nombre' required></Field>

                    <label htmlFor='company'>Empresa:</label>
                    <Field className='field' id='company' name='company' placeholder='Empresa' required></Field>

                    <label htmlFor='email'>Email:</label>
                    <Field className='field' id='email' name='email' placeholder='Email' required></Field>

                    <label htmlFor='description'>Descripcion:</label>
                    <Field className='area' as='textarea' id='description' name='description' placeholder='Descripcion' required></Field>
                    
                    {/* Botón de envío del formulario */}
                    <button type="submit">Enviar</button>
                </Form>
            </Formik>
        </div>
    );
}

export default ContactForm;
