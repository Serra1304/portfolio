package es.gtorres.backend.controller;

import es.gtorres.backend.controllers.CertificateController;
import es.gtorres.backend.entities.Certificate;
import es.gtorres.backend.services.CertificateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificateControllerTest {

    @Mock
    private CertificateService certificateService;

    @InjectMocks
    private CertificateController certificateController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCertificate_Success() {
        Certificate validCertificate = createValidCertificate();
        when(certificateService.addCertificate(validCertificate)).thenReturn(ResponseEntity.ok("Certificado agregado correctamente"));

        ResponseEntity<String> response = certificateController.addCertificate(validCertificate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado agregado correctamente", response.getBody());
    }

    @Test
    public void testAddCertificate_ValidationFailure() {
        Certificate invalidCertificate = createInvalidCertificate();
        when(certificateService.addCertificate(invalidCertificate))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validación de datos incorrecta"));

        ResponseEntity<String> response = certificateController.addCertificate(invalidCertificate);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Validación de datos incorrecta", response.getBody());
    }

    @Test
    public void testAddCertificate_NullCertificate() {
        when(certificateService.addCertificate(null))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Certificado nulo"));

        ResponseEntity<String> response = certificateController.addCertificate(null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Certificado nulo", response.getBody());
    }

    @Test
    public void testFindAllCertificates_WithData() {
        List<Certificate> certificates = createCertificates();
        ResponseEntity<List<Certificate>> responseEntity = ResponseEntity.ok(certificates);
        doReturn(responseEntity).when(certificateService).findAllCertificates();

        ResponseEntity<?> response = certificateController.findAllCertificates();

        assertEquals(HttpStatus.OK, certificateController.findAllCertificates().getStatusCode());
        assertEquals(certificates, response.getBody());
    }

    @Test
    public void testFindAllCertificates_EmptyData() {
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ningún certificado encontrado");
        doReturn(responseEntity).when(certificateService).findAllCertificates();

        ResponseEntity<?> response = certificateController.findAllCertificates();

        assertEquals(HttpStatus.NO_CONTENT, certificateController.findAllCertificates().getStatusCode());
        assertEquals("Ningún certificado encontrado", response.getBody());
    }

    @Test
    public void testFindAllCertificates_DataBaseError() {
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(certificateService).findAllCertificates();

        ResponseEntity<?> response = certificateController.findAllCertificates();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testFindCertificateById_Success() {
        String validId = "validId";
        Certificate certificate = createValidCertificate();
        ResponseEntity<Certificate> responseEntity = ResponseEntity.ok(certificate);
        doReturn(responseEntity).when(certificateService).findCertificateById(validId);

        ResponseEntity<?> response = certificateController.findCertificateById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(certificate, response.getBody());
    }

    @Test
    public void testFindCertificateById_InvalidId() {
        String invalidId = "invalidId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(certificateService).findCertificateById(invalidId);

        ResponseEntity<?> response = certificateController.findCertificateById(invalidId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testFindCertificateById_CertificateNoFound() {
        String incorrectId = "incorrectId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró certificado con ese identificador");
        doReturn(responseEntity).when(certificateService).findCertificateById(incorrectId);

        ResponseEntity<?> response = certificateController.findCertificateById(incorrectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró certificado con ese identificador", response.getBody());
    }

    @Test
    public void testFindCertificateById_DataBaseError() {
        String correctId = "correctId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(certificateService).findCertificateById(correctId);

        ResponseEntity<?> response = certificateController.findCertificateById(correctId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testUpdateCertificateById_Success() {
        String validId = "validId";
        Map<String, String> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Certificado actualizado con éxito");
        doReturn(responseEntity).when(certificateService).updateCertificateById(validId, validData);

        ResponseEntity<?> response = certificateController.updateCertificateById(validId, validData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado actualizado con éxito", response.getBody());
    }

    @Test
    public void testUpdateCertificateById_InvalidId() {
        String invalidId = "invalidId";
        Map<String, String> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(certificateService).updateCertificateById(invalidId, validData);

        ResponseEntity<?> response = certificateController.updateCertificateById(invalidId, validData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testUpdateCertificateById_InvalidData() {
        String validId = "validId";
        Map<String, String> invalidData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos no validos");
        doReturn(responseEntity).when(certificateService).updateCertificateById(validId, invalidData);

        ResponseEntity<?> response = certificateController.updateCertificateById(validId, invalidData);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Datos no validos", response.getBody());
    }

    @Test
    public void testUpdateCertificateById_CertificateNoFound() {
        String incorrectId = "incorrectId";
        Map<String, String> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró certificado con ese identificador");
        doReturn(responseEntity).when(certificateService).updateCertificateById(incorrectId, validData);

        ResponseEntity<?> response = certificateController.updateCertificateById(incorrectId, validData);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró certificado con ese identificador", response.getBody());
    }

    @Test
    public void testUpdateCertificateById_DataBaseError() {
        String correctId = "correctId";
        Map<String, String> validData = new HashMap<>();
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(certificateService).updateCertificateById(correctId, validData);

        ResponseEntity<?> response = certificateController.updateCertificateById(correctId, validData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    @Test
    public void testDeleteCertificateById_Success() {
        String validId = "validId";
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Certificado eliminado correctamente");
        doReturn(responseEntity).when(certificateService).deleteById(validId);

        ResponseEntity<?> response = certificateController.deleteCertificateById(validId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Certificado eliminado correctamente", response.getBody());
    }

    @Test
    public void testDeleteCertificateById_InvalidId() {
        String invalidId = "invalidId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Identificador no valido");
        doReturn(responseEntity).when(certificateService).deleteById(invalidId);

        ResponseEntity<?> response = certificateController.deleteCertificateById(invalidId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Identificador no valido", response.getBody());
    }

    @Test
    public void testDeleteCertificateById_CertificateNoFound() {
        String incorrectId = "incorrectId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró certificado con ese identificador");
        doReturn(responseEntity).when(certificateService).deleteById(incorrectId);

        ResponseEntity<?> response = certificateController.deleteCertificateById(incorrectId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró certificado con ese identificador", response.getBody());
    }

    @Test
    public void testDeleteCertificateById_DataBaseError() {
        String correctId = "correctId";
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al acceder a la base de datos");
        doReturn(responseEntity).when(certificateService).deleteById(correctId);

        ResponseEntity<?> response = certificateController.deleteCertificateById(correctId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al acceder a la base de datos", response.getBody());
    }

    private Certificate createValidCertificate() {
        return new Certificate(
                "Esto es una descripción",
                "Esta es la fecha",
                "Esta es la dirección url de la imagen del certificado",
                "Esta es la dirección url del archivo del certificado");
    }

    private Certificate createInvalidCertificate() {
        return new Certificate(
                "Esto es una descripción",
                "",
                "Esta es la dirección url de la imagen del certificado",
                "Esta es la dirección url del archivo del certificado");
    }

    private List<Certificate> createCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        certificates.add(createValidCertificate());
        certificates.add(createValidCertificate());
        certificates.add(createValidCertificate());

        return certificates;
    }
}

