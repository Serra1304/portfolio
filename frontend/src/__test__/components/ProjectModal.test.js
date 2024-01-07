import React from 'react';
import { render } from '@testing-library/react';
import ProjectModal from '../../components/ProjectModal';
import '@testing-library/jest-dom/extend-expect'

describe('Componente ProjectModal', () => {
    const project = {
        title: 'Titulo del proyecto',
        imageSrc: 'Imagen.jpg',
        skills: ['React', 'JavaScript'],
        description: 'Ejemplo de descripcion del proyecto',
    };

    test('Renderizado del componente ProjectModal', () => {
        const onClose = jest.fn();

        const { getByText, getByAltText } = render(<ProjectModal project={project} onClose={onClose} />);

        // Verifica el título y la descripción
        const titleElement = getByText('Titulo del proyecto');
        const descriptionElement = getByText('Ejemplo de descripcion del proyecto');

        expect(titleElement).toBeInTheDocument();
        expect(descriptionElement).toBeInTheDocument();

        // Verifica la imagen
        const imageElement = getByAltText('Titulo del proyecto');
        expect(imageElement).toBeInTheDocument();
        expect(imageElement).toHaveAttribute('src', 'Imagen.jpg');


        // Verifica los skills
        project.skills.forEach((skill) => {
            const skillElement = getByText(skill);
            expect(skillElement).toBeInTheDocument();
        });
    });

    test('Limitar propagacion del click en los componentes internos', () => {
        const onClose = jest.fn();
        const { container } = render(<ProjectModal project={project} onClose={onClose} />);

        const modalContainer = container.querySelector('.modal-container');
        modalContainer.click();

        expect(onClose).toHaveBeenCalledTimes(0);
    });

    test('Cerrar modal en los componentes externos', () => {
        const onClose = jest.fn();
        const { container } = render(<ProjectModal project={project} onClose={onClose} />);

        const modalPage = container.querySelector('.modal-page');
        modalPage.click();

        expect(onClose).toHaveBeenCalledTimes(1);
    });
});
