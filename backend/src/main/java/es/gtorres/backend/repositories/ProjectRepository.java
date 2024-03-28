package es.gtorres.backend.repositories;

import es.gtorres.backend.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de proyectos.
 * Este repositorio proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre entidades de tipo {@link Project}.
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
