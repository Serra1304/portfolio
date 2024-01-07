import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import ProjectCard from '../../components/ProjectCard';
import '@testing-library/jest-dom/extend-expect'


describe('Componente ProjectCard', () => {
  const mockProps = {
    title: 'Sample Project',
    description: '<p>Sample description</p>',
    imageSrc: 'sample-image.jpg',
    onClick: jest.fn(),
  };

  test('Renderizado del componente ProjectCard', () => {
    render(<ProjectCard {...mockProps} />);
    const titleElement = screen.getByText(mockProps.title);
    expect(titleElement).toBeInTheDocument();
  });

  test('Muestra el titulo correctamente', () => {
    render(<ProjectCard {...mockProps} />);
    const titleElement = screen.getByText(mockProps.title);
    expect(titleElement).toBeInTheDocument();
  });

  test('Representa la descripción usando dangerouslySetInnerHTML', () => {
    render(<ProjectCard {...mockProps} />);
    const descriptionElement = screen.getByText('Sample description', { exact: false });
    expect(descriptionElement).toBeInTheDocument();
  });

  test('Llama a la función onClick cuando se hace clic en la tarjeta', () => {
    render(<ProjectCard {...mockProps} />);
    const cardElement = screen.getByTestId('project-card');
    fireEvent.click(cardElement);
    expect(mockProps.onClick).toHaveBeenCalled();
  });

  test('Muestra la imagen con src y alt correctos', () => {
    render(<ProjectCard {...mockProps} />);
    const imgElement = screen.getByAltText(mockProps.title);
    expect(imgElement).toHaveAttribute('src', mockProps.imageSrc);
  });

  test('No falla sin la propiedad onClick', () => {
    const propsWithoutClick = { ...mockProps, onClick: undefined };
    render(<ProjectCard {...propsWithoutClick} />);
    const cardElement = screen.getByTestId('project-card');
    fireEvent.click(cardElement);
    expect(cardElement).toBeInTheDocument();
  });
});
