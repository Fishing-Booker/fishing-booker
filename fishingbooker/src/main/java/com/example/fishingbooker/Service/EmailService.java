package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IEmailService;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailAfterReservation(Integer clientId)  {
        User user = userRepository.getById(clientId);
        String subject = "Confirmation of reservation";
        String sender = "Fishing Booker";
        String content ="<p>Dear " + user.getName() + " " + user.getSurname() + ", </p>";
        content += "<p>Thank you for choosing us to make a reservation!</p>";
        content += "<p>We are looking forward to see you!<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@gmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);

    }
}
