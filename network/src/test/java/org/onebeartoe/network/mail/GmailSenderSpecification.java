
package org.onebeartoe.network.mail;

import java.io.File;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.net.ssl.SSLException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class GmailSenderSpecification
{
    private GmailSender implementation;
    
    private String fakeRecipients = "user@no-host.fake";
    
    @BeforeTest
    public void setup()
    {
        implementation = new GmailSender("jimmy", "fake-password");
    }
    
//    @Test(expectedExceptions = AuthenticationFailedException.class)
    public void sendMail_badCredentials() throws MessagingException
    {
        String subject = "s";
        String body = "b";
        
        implementation.sendMail(subject, body, fakeRecipients);
    }
  
// why test only for failure?    
//    @Test(expectedExceptions = MessagingException.class)
    public void sendMail_badCredentials_attachementAndCheckIdentity() throws MessagingException
    {
        File attachement = new File("pom.xml");
        boolean checkServerIdentity = true;
        
        implementation.sendMail("subject",
                                "body",
                                fakeRecipients,
                                attachement,
                                checkServerIdentity);
    }
}
