
package org.onebeartoe.network.mail;

import java.io.File;
import java.security.Security;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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

    
    private Properties buildProperties(int port)
    {
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
        
        return props;
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
    
    @Override
    public void sendMail(String subject, String body, String recipients, int port) throws AddressException, MessagingException 
    {
        File noAttachment = null;
        
        sendMail(subject, body, recipients, port, noAttachment);
    }    

    public synchronized void sendMail(String subject, String body, String recipients, int port, File attachment) throws AddressException, MessagingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = buildProperties(port);

        Session session = Session.getInstance(props, new Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {	
                String user = getSender();

                return new PasswordAuthentication(user, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        String sender = getSender();
        
        message.setSender(new InternetAddress(sender));

        message.setSubject(subject);
        
        if(attachment == null)
        {
            // just send a regular test-only email
            message.setContent(body, "text/plain");
        }
        else
        {
            // Create the multi-part
            Multipart multipart = new MimeMultipart();

            // Create part one
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(body);

            // Add the first part
            multipart.addBodyPart(messageBodyPart);

            String filename = attachment.getName();

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(
               new DataHandler(source));
            messageBodyPart.setFileName(filename);

            // Add the second part
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);
        }

        if (recipients.indexOf(',') > 0)
        {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        }
        else
        {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
        }
        
        Transport.send(message);
    }
}
