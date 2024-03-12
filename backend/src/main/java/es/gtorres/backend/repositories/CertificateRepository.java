package es.gtorres.backend.repositories;

import es.gtorres.backend.entities.Certificate;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para gestionar certificados en la base de datos.
 * Extiende la interfaz {@link GenericRepository} con la entidad {@link Certificate} y String como tipo de ID.
 */
@Repository
public interface CertificateRepository extends GenericRepository<Certificate, String> {
}
