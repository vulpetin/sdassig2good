package com.sd.assig23.service;

import com.sd.assig23.dto.RestaurantDTO;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

@Service
public class EmailService {

    private java.util.logging.Logger logger;

    public EmailService(){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(this.getClass().getName()+".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }



    /**
     * sends email about new restaurant
     *
     * @param dto of the Restaurant
     */
    public void sendMail(RestaurantDTO dto){

        // Recipient's email ID needs to be mentioned.
        String to = "vulpetin@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "sdassig2@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("sdassig2@gmail.com", "qwertyuiop1!");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("New Restaurant!");

            // Now set the actual message
            message.setText("New Restaurant Added: " + dto.toString());

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            logger.info("Sent email successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


}
