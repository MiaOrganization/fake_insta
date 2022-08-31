package lnstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistaService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("olicaico98@gmail.com", "olicaico98@gmail.com", "olicaico98@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    @Transactional
    public Boolean inviaPassword(String password) {
        return true;
    }

}






