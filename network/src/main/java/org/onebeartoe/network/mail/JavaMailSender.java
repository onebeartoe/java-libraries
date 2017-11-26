package org.onebeartoe.network.mail;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface JavaMailSender 
{
    public void sendMail(String subject, String body, String recipients) throws AddressException, MessagingException;
    
    public void sendMail(String subject, String body, String recipients, File attachment) throws AddressException, MessagingException;
}
