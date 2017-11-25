
package org.onebeartoe.network.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author Roberto Marquez
 */
public class AttSender extends GmailSender
{    
    public AttSender(String user, String password) 
    {
        super(user, password);
        
        smtphost = "outbound.att.net";
    }
    
    protected String getSender()
    {
        String sender = username + "@att.net";
        
        return sender;
    }
    
    public static void main(String [] args) throws MessagingException
    {
        String user = "duplicator-pi";
        
        // The password is looked up as an environment variable.
        String key = "SMTP_PASSWORD";
        String pw = System.getProperty(key, "");

        String to = "onebeartoe@gmail.com";
        String subject = "calling beto from att";
        String body = "real far far out body";
        
        AttSender sender = new AttSender(user, pw);
        sender.sendMail(subject, body, to);
    }

    @Override
    public synchronized void sendMail(String subject, String body, String recipients) throws AddressException, MessagingException
    {
//        int port = 587;
        int port = 465;

        sendMail(subject, body, recipients, port);
    }

    @Override
    public synchronized void sendMail(String subject, String body, String recipients, int port) throws AddressException, MessagingException
    {
        super.sendMail(subject, body, recipients, port);
    }    
}
