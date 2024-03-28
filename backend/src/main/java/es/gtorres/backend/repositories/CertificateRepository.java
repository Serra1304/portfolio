package es.gtorres.backend.repositories;

import es.gtorres.backend.entities.Certificate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la gestión de certificados.
 * Este repositorio proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre entidades de tipo {@link Certificate}.
 */
@Repository
public interface CertificateRepository extends MongoRepository<Certificate, String> {
}
