
package org.onebeartoe.network.mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A class to send email via the GMail service
 * <br/>
 * A valid gmail account is required
 * @author rob
 *
 */
public class GmailSender implements JavaMailSender 
{
	
    protected String smtphost = "smtp.gmail.com";
    protected String username;
    protected String password;

    public GmailSender(String user, String password)
    {
            this.username = user;
            this.password = password;
    }
    
    protected String getSender()
    {
        String sender = username + "@gmail.com";
        
        return sender;
    }

    /* (non-Javadoc)
     * @see onebeartoe.mail.google.JavaMailSender#sendMail(java.lang.String, java.lang.String, java.lang.String)
     */
    public synchronized void sendMail(String subject, String body, String recipients) throws AddressException, MessagingException
    {
        int port = 465;

        sendMail(subject, body, recipients, port);
    }

    public synchronized void sendMail(String subject, String body, String recipients, int port) throws AddressException, MessagingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", smtphost);
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.ssl.enable", "false");
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", String.valueOf(port) );
        props.put("mail.smtp.socketFactory.port", String.valueOf(port) );
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() 
                        {
                            protected PasswordAuthentication getPasswordAuthentication() 
                            {	
                                String user = getSender();
//                                String user = username + "@att.net";
                                
                                return new PasswordAuthentication(user, password);
                            }
                        });

        MimeMessage message = new MimeMessage(session);

        String sender = getSender();
        
        message.setSender(new InternetAddress(sender));

        message.setSubject(subject);
        message.setContent(body, "text/plain");
        if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress
                                .parse(recipients));
        else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                                recipients));

        Transport.send(message);
        
    }
}
