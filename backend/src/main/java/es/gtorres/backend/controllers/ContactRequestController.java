package es.gtorres.backend.controllers;

import es.gtorres.backend.entities.ContactRequest;
import es.gtorres.backend.repositories.ContactRequestRepository;
import es.gtorres.backend.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactRequestController {

    private ContactRequestRepository repository;
    private MailService mailService;

    @Autowired
    public ContactRequestController(ContactRequestRepository contactRequestRepository, MailService mailService) {
        this.repository = contactRequestRepository;
        this.mailService = mailService;
    }

    @PostMapping("/contactRequest")
    public ResponseEntity<String> contactRequest(@RequestBody ContactRequest contact) {
        contact.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        repository.save(contact);

        mailService.sendMailAdmin(contact);
        mailService.sendMailUser(contact);

        return ResponseEntity.ok("Solicitud de contacto enviada correctamente");
    }

    @GetMapping("/allRequest")
    public List<ContactRequest> allRequest() {
        return repository.findAll();
    }

    @GetMapping("/lastRequest")
    public ContactRequest lastRequest() {
        return repository.findAll().get(repository.findAll().size() - 1);
    }

    @GetMapping("/findRequestById/{id}")
    public ContactRequest findRequestById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
