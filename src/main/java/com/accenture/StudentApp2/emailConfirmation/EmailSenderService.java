package com.accenture.StudentApp2.emailConfirmation;

import com.accenture.StudentApp2.repository.ConfirmationTokenRepository;
import com.accenture.StudentApp2.userModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(User user) {

        VerificationToken verificationToken = new VerificationToken(user);
        confirmationTokenRepository.save(verificationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("innapbm@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+verificationToken.getConfirmationToken());

        //javaMailSender.send(email);
        System.out.println("Email was sent to " + mailMessage.getTo() + ": " + mailMessage.getText());
    }


}
