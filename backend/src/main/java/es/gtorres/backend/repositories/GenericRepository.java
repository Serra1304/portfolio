package es.gtorres.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interfaz de repositorio genérico que proporciona operaciones básicas de CRUD.
 * Esta interfaz extiende {@link MongoRepository} y sirve como un repositorio base para otros repositorios.
 * @param <T> el tipo de la entidad a gestionar.
 * @param <ID> el tipo del identificador de la entidad.
 */
@NoRepositoryBean
public interface GenericRepository<T,ID> extends MongoRepository<T,ID> {
}
