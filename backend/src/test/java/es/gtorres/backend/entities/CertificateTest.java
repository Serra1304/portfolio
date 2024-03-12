package es.gtorres.backend.entities;

import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
import jakarta.validation.Validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testWithBlankField() {
        Certificate certificate1 = new Certificate("", "date", "imgUrl", "certUrl");
        Certificate certificate2 = new Certificate("description", "", "imgUrl", "certUrl");
        Certificate certificate3 = new Certificate("description", "date", "", "certUrl");
        Certificate certificate4 = new Certificate("description", "date", "imgUrl", "");

        assertFalse(validator.validate(certificate1).isEmpty());
        assertFalse(validator.validate(certificate2).isEmpty());
        assertFalse(validator.validate(certificate3).isEmpty());
        assertFalse(validator.validate(certificate4).isEmpty());
    }

    @Test
    public void testWithNullField() {
        Certificate certificate1 = new Certificate(null, "date", "imgUrl", "certUrl");
        Certificate certificate2 = new Certificate("description", null, "imgUrl", "certUrl");
        Certificate certificate3 = new Certificate("description", "date", null, "certUrl");
        Certificate certificate4 = new Certificate("description", "date", "imgUrl", null);

        assertFalse(validator.validate(certificate1).isEmpty());
        assertFalse(validator.validate(certificate2).isEmpty());
        assertFalse(validator.validate(certificate3).isEmpty());
        assertFalse(validator.validate(certificate4).isEmpty());
    }

    @Test
    public void testConstructorAndGetters() {
        String description = "description";
        String date = "date";
        String imgUrl = "imgUrl";
        String certUrl = "certUrl";

        Certificate certificate = new Certificate(description, date, imgUrl, certUrl);

        assertEquals(description, certificate.getDescription());
        assertEquals(date, certificate.getDate());
        assertEquals(imgUrl, certificate.getImageUrl());
        assertEquals(certUrl, certificate.getCertUrl());
    }

    @Test
    public void testSetters() {
        String description = "description";
        String date = "date";
        String imgUrl = "imgUrl";
        String certUrl = "certUrl";

        Certificate certificate = new Certificate();

        certificate.setDescription(description);
        certificate.setDate(date);
        certificate.setImageUrl(imgUrl);
        certificate.setCertUrl(certUrl);

        assertEquals(description, certificate.getDescription());
        assertEquals(date, certificate.getDate());
        assertEquals(imgUrl, certificate.getImageUrl());
        assertEquals(certUrl, certificate.getCertUrl());
    }
}
