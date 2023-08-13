package es.gtorres.backend.repositories;

import es.gtorres.backend.entities.ContactRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRequestRepository extends MongoRepository<ContactRequest, String> {
}
