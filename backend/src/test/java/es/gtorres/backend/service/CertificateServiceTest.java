package es.gtorres.backend.service;

import es.gtorres.backend.entities.Certificate;
import es.gtorres.backend.repositories.CertificateRepository;
import es.gtorres.backend.services.CertificateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceTest {

    @Mock
    private CertificateRepository certificateRepository;

    @InjectMocks
    private CertificateService certificateService;

    @Test
    public void testAddCertificate_Success() {
        Certificate certificate = new Certificate();
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<String> response = certificateService.addCertificate(certificate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(certificateRepository, times(1)).save(certificate);
    }

    @Test
    public void testAddCertificate_Failure() {
        when(certificateRepository.save(any())).thenThrow(new DataIntegrityViolationException("Error al guardar el certificado"));

        ResponseEntity<String> response = certificateService.addCertificate(new Certificate());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testFindAllCertificates_NoContent() {
        when(certificateRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = certificateService.findAllCertificates();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllCertificates_Success() {
        when(certificateRepository.findAll()).thenReturn(certificateList());

        ResponseEntity<?> response = certificateService.findAllCertificates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllCertificates_Failure() {
        when(certificateRepository.findAll()).thenThrow(new DataIntegrityViolationException("Error al obtener la lista de certificados"));

        ResponseEntity<?> response = certificateService.findAllCertificates();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(certificateRepository, times(1)).findAll();
    }

    @Test
    public void testFindCertificateById_Success() {
        Certificate certificate = new Certificate();
        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));

        ResponseEntity<?> response = certificateService.findCertificateById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(certificate, response.getBody());
        verify(certificateRepository, times(1)).findById("123");
    }

    @Test
    public void testFindCertificateById_BlankFieldError() {
        String id = "";
        ResponseEntity<?> response = certificateService.findCertificateById(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById(id);
    }

    @Test
    public void testFindCertificateById_NullFieldError() {
        ResponseEntity<?> response = certificateService.findCertificateById(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById(null);
    }

    @Test void testFindCertificateById_InvalidFieldError() {
        String id = "Id_no_existe";
        ResponseEntity<?> response = certificateService.findCertificateById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(certificateRepository, times(1)).findById(id);
    }

    @Test
    public void testFindCertificatesById_Failure() {
        String id = "123";
        when(certificateRepository.findById(id)).thenThrow(new DataIntegrityViolationException("Error al obtener el certificado indicado"));

        ResponseEntity<?> response = certificateService.findCertificateById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(certificateRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateCertificateById_SuccessWhitDescription() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated Description");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado correctamente.", response.getBody());
        assertEquals("Updated Description", certificate.getDescription());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateCertificateById_SuccessWhitDate() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("date", "Updated Date");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado correctamente.", response.getBody());
        assertEquals("Updated Date", certificate.getDate());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateCertificateById_SuccessWhitImageUrl() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("imageUrl", "Updated imageUrl");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado correctamente.", response.getBody());
        assertEquals("Updated imageUrl", certificate.getImageUrl());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateCertificateById_SuccessWhitCertUrl() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("certUrl", "Updated certUrl");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado correctamente.", response.getBody());
        assertEquals("Updated certUrl", certificate.getCertUrl());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateCertificateById_SuccessFields() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated description");
        data.put("date", "Updated date");
        data.put("imageUrl", "Updated imageUrl");
        data.put("certUrl", "Updated certUrl");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));
        when(certificateRepository.save(any())).thenReturn(certificate);

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado correctamente.", response.getBody());
        assertEquals("Updated description", certificate.getDescription());
        assertEquals("Updated date", certificate.getDate());
        assertEquals("Updated imageUrl", certificate.getImageUrl());
        assertEquals("Updated certUrl", certificate.getCertUrl());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateCertificateById_InvalidField() {
        Certificate certificate = new Certificate();
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated description");
        data.put("invalidField", "Updated date");
        data.put("imageUrl", "Updated imageUrl");
        data.put("certUrl", "Updated certUrl");

        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));

        ResponseEntity<?> response = certificateService.updateCertificateById("123", data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateCertificateById_BlankIdError() {
        String id = "";
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = certificateService.updateCertificateById(id, data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById(id);
        verify(certificateRepository, times(0)).save(any());
    }

    @Test
    public void testUpdateCertificateById_NullIdError() {
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = certificateService.updateCertificateById(null, data);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById(null);
        verify(certificateRepository, times(0)).save(any());
    }

    @Test void testUpdateCertificateById_InvalidIdError() {
        String id = "Id_no_existe";
        Map<String, String> data = new HashMap<>();
        data.put("description", "Updated description");
        ResponseEntity<?> response = certificateService.updateCertificateById(id, data);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(certificateRepository, times(1)).findById(id);
        verify(certificateRepository, times(0)).save(any());
    }

    @Test
    public void testDeleteById_Success() {
        Certificate certificate = new Certificate();
        when(certificateRepository.findById(any())).thenReturn(Optional.of(certificate));

        ResponseEntity<String> response = certificateService.deleteById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(certificateRepository, times(1)).findById("123");
        verify(certificateRepository, times(1)).deleteById("123");
    }

    @Test
    public void testDeleteCertificateById_BlankIdError() {
        ResponseEntity<String> response = certificateService.deleteById("");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById("");
        verify(certificateRepository, times(0)).deleteById("");
    }

    @Test
    public void testDeleteCertificateById_NullIdError() {
        ResponseEntity<String> response = certificateService.deleteById(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(certificateRepository, times(0)).findById(null);
        verify(certificateRepository, times(0)).deleteById(null);
    }

    @Test void testDeleteCertificateById_InvalidIdError() {
        ResponseEntity<String> response = certificateService.deleteById("Invalid_ID");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(certificateRepository, times(1)).findById("Invalid_ID");
        verify(certificateRepository, times(0)).deleteById("Invalid_ID");
    }

    private List<Certificate> certificateList() {
        List<Certificate> list = new ArrayList<>();
        Certificate certificate = new Certificate("description", "date", "imageUrl", "certUrl");
        list.add(certificate);
        list.add(certificate);
        list.add(certificate);

        return list;
    }
}

