package com.lcwd;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("preparing to send message");
        String message = "hello this is message for security check";
        String subject = "Confermation message";
        String to = "abhishek.kr6243@gmail.com";
        String from = "desihippocrates@gmail.com";

        sendEmail(message,subject,to,from);

    }


    //this is responsible to send email..
    private static void sendEmail(String message, String subject, String to, String from) {

        //Variable for gmail host
        String host = "smtp.gmail.com";

        //Get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.host", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //step 1 : to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("desihippocrates@gmail.com", "password");
            }
        });

        session.setDebug(true);

        //step 2: compose the message [text, multimedia]
        MimeMessage m = new MimeMessage(session);

        try{
            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //send the message using transport class
            Transport.send(m);

            System.out.println("Send success........");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
