
package org.onebeartoe.network.mail;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class GmailSenderSpecification
{
    private GmailSender implementation;
    
    @BeforeTest
    public void setup()
    {
        implementation = new GmailSender("jimmy", "fake-password");
    }
    
    @Test(expectedExceptions = AuthenticationFailedException.class)
    public void sendMail() throws MessagingException
    {
        String subject = "s";
        String body = "b";
        String recipients = "user@no-host.fake";
        
        implementation.sendMail(subject, body, recipients);
    }
}
