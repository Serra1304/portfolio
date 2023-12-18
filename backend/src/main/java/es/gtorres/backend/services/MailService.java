package es.gtorres.backend.services;

import es.gtorres.backend.entities.ContactRequest;
import es.gtorres.backend.repositories.ContactRequestRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Servicio para enviar correos electrónicos relacionados con las solicitudes de contacto.
 */
@Component
public class MailService{

    @Value("${mailFrom}")
    private String mailFrom;
    @Value("${mailWebMaster}")
    private String mailWebMaster;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final ContactRequestRepository contactRequestRepository;

    /**
     * Constructor de la clase MailService.
     * @param javaMailSender Instancia de JavaMailSender para enviar correos.
     * @param templateEngine Motor de plantillas para generar contenido HTML.
     * @param contactRequestRepository Repositorio para gestionar las solicitudes de contacto.
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine,
                       ContactRequestRepository contactRequestRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.contactRequestRepository = contactRequestRepository;
    }

    /**
     * Envía un correo electrónico simple.
     * @param simpleMessages El mensaje simple a enviar por correo electrónico.
     * @throws MailException si hay un error al enviar el correo electrónico.
     */
    public void send(SimpleMailMessage simpleMessages) throws MailException {
        javaMailSender.send(simpleMessages);
    }

    /**
     * Envía un correo electrónico utilizando un SimpleMailMessage.
     * @param simpleMessages Array de SimpleMailMessage a enviar.
     * @throws MailException Si hay un error al enviar el correo.
     */
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for (SimpleMailMessage message : simpleMessages) {
            javaMailSender.send(message);
        }
    }

    /**
     * Envía un correo electrónico al administrador sobre una solicitud de contacto.
     * @param contact La solicitud de contacto.
     * @return true si el correo se envió correctamente, false si ocurrió un error.
     */
    public boolean sendMailAdmin(ContactRequest contact) {
        try {
            String template = loadMailAdmin(contact);

            MimeMessage message1 = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message1, true, "UTF-8");

            ClassPathResource imageResource = new ClassPathResource("static/images/logo.png");

            helper.setFrom(mailFrom);
            helper.setTo(mailWebMaster);
            helper.setSubject("Solicitud de contacto");
            helper.setText(template, true);
            helper.addInline("logo", imageResource);

            javaMailSender.send(message1);

            return true;

        } catch (MessagingException e) {
            return false;
        }
    }

    /**
     * Envía un correo electrónico al usuario que realizó la solicitud de contacto.
     * @param contact La solicitud de contacto.
     * @return true si el correo se envió correctamente, false si ocurrió un error.
     */
    public boolean sendMailUser(ContactRequest contact) {
        try {
            String template = loadMailUser(contact);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            ClassPathResource imageResource = new ClassPathResource("static/images/logo.png");

            helper.setFrom(mailFrom);
            helper.setTo(contact.getEmail());
            helper.setSubject("GTorres (no-reply)");
            helper.setText(template, true);
            helper.addInline("logo", imageResource);

            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    /**
     * Carga el contenido del correo electrónico para el administrador basado en la solicitud de contacto.
     * @param contact La solicitud de contacto.
     * @return El contenido del correo electrónico para el administrador.
     */
    private String loadMailAdmin(ContactRequest contact) {
        Context context = new Context();
        context.setVariable("username", contact.getName());
        context.setVariable("useremail", contact.getEmail());
        context.setVariable("companyname", contact.getCompany());
        context.setVariable("message", contact.getDescription());

        return templateEngine.process("MailAdmin", context);
    }

    /**
     * Carga el contenido del correo electrónico para el usuario basado en la solicitud de contacto.
     * @param contact La solicitud de contacto.
     * @return El contenido del correo electrónico para el usuario.
     */
    private String loadMailUser(ContactRequest contact) {
        Context context = new Context();
        context.setVariable("username", contact.getName());

        return templateEngine.process("MailUser", context);
    }
}

