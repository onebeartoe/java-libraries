
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
    protected int smtpPort;
    protected String username;
    protected String password;
    
    public GmailSender(String user, String password)
    {
            this.username = user;
            this.password = password;
            
            smtpPort = 465;
    }

    private Properties buildProperties()
    {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", smtphost);
        props.put("mail.smtp.starttls.enable", "true");
        
        props.put("mail.smtp.ssl.enable", "true");
        //props.put("mail.smtp.ssl.enable", "false");
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", String.valueOf(smtpPort) );
        props.put("mail.smtp.socketFactory.port", String.valueOf(smtpPort) );
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        
System.out.println("checkserveridentity not used");
//      This next one was added due to a SonarQube security item, 
//      but setting it actually cuases this error:
//           DEBUG SMTP: exception reading response: javax.net.ssl.SSLHandshakeException: Server chose TLSv1.2, but that protocol version is not enabled or not supported by the client.
//           Exception in thread "main" javax.mail.MessagingException
//TODO: verify the next property, found by SonarQube, is actually recommended and if not remove this commented code.
//        props.put("mail.smtp.ssl.checkserveridentity", true);
        
        props.setProperty("mail.smtp.quitwait", "false");
        
        return props;
    }
    
    protected String getSender()
    {
        String sender = username + "@gmail.com";
        
        return sender;
    }
    
    @Override
    public void sendMail(String subject, String body, String recipients) throws AddressException, MessagingException 
    {
        File noAttachment = null;
        
        sendMail(subject, body, recipients, noAttachment);
    }    

    @Override
    public synchronized void sendMail(String subject, String messageText, String recipients, File attachment) throws AddressException, MessagingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = buildProperties();

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
            message.setContent(messageText, "text/plain");
        }
        else
        {
            // Create the multi-part
            Multipart multipart = new MimeMultipart();

            // Create part one
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(messageText);

            // Add the first part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            messageBodyPart.setDataHandler( new DataHandler(source));
            
            String filename = attachment.getName();
            messageBodyPart.setFileName(filename);

            // Add the second part
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);
        }

        if ( recipients.contains(",") )
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
