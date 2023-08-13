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

@Component
public class MailService{

    @Value("${mailFrom}")
    private String mailFrom;
    @Value("${mailWebMaster}")
    private String mailWebMaster;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final ContactRequestRepository contactRequestRepository;

    @Autowired
    public MailService(JavaMailSender javaMailSender, TemplateEngine templateEngine,
                       ContactRequestRepository contactRequestRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.contactRequestRepository = contactRequestRepository;
    }

    public void send(SimpleMailMessage simpleMessages) throws MailException {
        javaMailSender.send(simpleMessages);
    }

    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for (SimpleMailMessage message : simpleMessages) {
            javaMailSender.send(message);
        }
    }

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

    private String loadMailAdmin(ContactRequest contact) {
        Context context = new Context();
        context.setVariable("username", contact.getName());
        context.setVariable("useremail", contact.getEmail());
        context.setVariable("companyname", contact.getCompany());
        context.setVariable("message", contact.getDescription());

        return templateEngine.process("MailAdmin", context);
    }

    private String loadMailUser(ContactRequest contact) {
        Context context = new Context();
        context.setVariable("username", contact.getName());

        return templateEngine.process("MailUser", context);
    }
}

