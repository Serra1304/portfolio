package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.ContactRequest;
import es.gtorres.backend.repositories.ContactRequestRepository;
import es.gtorres.backend.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para manejar las solicitudes de contacto.
 */
@RestController
@RequestMapping("/contact")
public class ContactRequestController {
    private final ContactRequestRepository repository;
    private final MailService mailService;

    /**
     * Constructor para el controlador de solicitudes de contacto.
     * @param contactRequestRepository Repositorio de solicitudes de contacto.
     * @param mailService Servicio para enviar correos electrónicos.
     */
    @Autowired
    public ContactRequestController(ContactRequestRepository contactRequestRepository, MailService mailService) {
        this.repository = contactRequestRepository;
        this.mailService = mailService;
    }

    /**
     * Endpoint para crear una nueva solicitud de contacto.
     * @param contact Objeto ContactRequest con la información de la solicitud.
     * @return ResponseEntity con un mensaje indicando el estado de la solicitud.
     */
    @PostMapping("/createContactRequest")
    public ResponseEntity<String> createContactRequests(@RequestBody ContactRequest contact) {
        if (contact.getName() == null || contact.getCompany() == null || contact.getEmail() == null || contact.getDescription() == null) {
            return ResponseEntity.badRequest().body("Faltan datos obligatorios en la solicitud de contacto.");
        }

        try {
            repository.save(contact);
            mailService.sendMailAdmin(contact);
            mailService.sendMailUser(contact);
            return ResponseEntity.ok("Solicitud de contacto enviada correctamente");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud de contacto");
        }
    }

    /**
     * Endpoint para obtener todas las solicitudes de contacto.
     * @return Lista de ContactRequest con todas las solicitudes.
     */
    @GetMapping("/contactRequests")
    public List<ContactRequest> allContactRequests() {
        return repository.findAll();
    }

    /**
     * Endpoint para obtener la última solicitud de contacto.
     * @return Lista con la última solicitud de contacto o lista vacía si no hay ninguna.
     */
    @GetMapping("/contactRequests/last")
    public List<ContactRequest> lastContactRequests() {
        List<ContactRequest> contactRequestList = repository.findAll();
        return contactRequestList.isEmpty()?
                Collections.emptyList():
                Collections.singletonList(contactRequestList.get(contactRequestList.size() -1));
    }

    /**
     * Endpoint para encontrar una solicitud de contacto por su ID.
     * @param id ID de la solicitud de contacto a buscar.
     * @return ResponseEntity con la solicitud encontrada o un mensaje de error si no se encuentra.
     */
    @GetMapping("/contactRequests/{id}")
    public ResponseEntity<ContactRequest> findContactRequestsById(@PathVariable String id) {
        Optional<ContactRequest> contactRequest = repository.findById(id);
        return contactRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Endpoint para eliminar una solicitud de contacto por su ID.
     * @param id ID de la solicitud de contacto a eliminar.
     * @return ResponseEntity con un mensaje indicando el resultado de la eliminación.
     */
    @DeleteMapping("/contactRequests/{id}")
    public ResponseEntity<String> deleteContactRequestById(@PathVariable String id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Solicitud de contacto eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error mientras se eliminaba la solicitud de contacto");
        }
    }
}
