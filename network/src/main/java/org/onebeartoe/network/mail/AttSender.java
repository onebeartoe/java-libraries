
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
        
//        smtpPort = 587;
        smtpPort = 465;
    }
    
    protected String getSender()
    {
        String sender = username + "@att.net";
        
        return sender;
    }

    @Override
    public synchronized void sendMail(String subject, String body, String recipients) throws AddressException, MessagingException
    {
        super.sendMail(subject, body, recipients);
    }    
}
