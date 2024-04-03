package kr.co.softsoldesk.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mail_account@artmee.co.kr");
        message.setTo(to);
        
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    /*public void sendEmailWithImage(String to, String subject, String text, String imagePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("mail_account@artmee.co.kr");
        helper.setTo(to);
        helper.setSubject(subject);
        
        // HTML 메일 본문 구성
        String content = "<html><body><p>" + text + "</p>"
                       + "<img src='" + imagePath + "'></body></html>";
        helper.setText(content, true); // true를 설정하여 HTML 형식임을 명시

        mailSender.send(message);
    }*/
}
