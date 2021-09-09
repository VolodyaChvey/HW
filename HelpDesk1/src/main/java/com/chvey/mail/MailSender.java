package com.chvey.mail;

import com.chvey.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Component
public class MailSender {

    private JavaMailSender mailSender;

    private TemplateEngine htmlTemplateEngine;
    @Autowired
    public MailSender(JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
        this.mailSender = mailSender;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");

    public void sendAcceptableEmail(List<User> recipients, Long id, EmailTemplate emailTemplate){

        Context context =new Context();
        context.setVariable("id",id);

        ExecutorService emailExecutorService = Executors.newSingleThreadExecutor();
        emailExecutorService.execute(()->{
            for (User user:recipients){
                try{
                    context.setVariable("user",user);
                    helper.setSubject(emailTemplate.getSubject());
                    helper.setFrom("chveyvladzimir@gmail.com");
                    helper.setTo("vladzimir1976@gmail.com");
                    String htmlContext = this.htmlTemplateEngine.process(emailTemplate.getTemplateName(),context);
                    helper.setText(htmlContext,true);
                    this.mailSender.send(mimeMessage);

                }catch (MessagingException e){

                }
            }
        });
    }
}
