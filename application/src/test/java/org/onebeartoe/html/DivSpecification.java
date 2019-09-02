
package org.onebeartoe.html;

import org.apache.commons.lang3.StringUtils;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * @author Roberto Marquez
 */
public class DivSpecification
{
    
    @Test
    public void toString_prettyPrint_nothingAdded()
    {
        Div implementation = new Div();
        PlainText plainText = new PlainText("some text");
        implementation.add(plainText);
        
        implementation.setPrettyPrint(true);
        
        String actual = implementation.toString();
        
        // assert no wrapper tags were added
        assertTrue( !actual.contains("<xhtml>") );
        assertTrue( !actual.contains("<html>") );
        assertTrue( !actual.contains("<body>") );
    }
    
    @Test
    public void toString_prettyPrint_nothingRemoved_emptyDiv()
    {
        Div implementation = new Div();

        String expected = implementation.toString();
        expected = StringUtils.deleteWhitespace(expected);
        
        implementation.setPrettyPrint(true);        
        String actual = implementation.toString();
        actual = StringUtils.deleteWhitespace(actual);
        
        // assert no wrapper tags were added
        assertTrue( actual.equals(expected) );
    }
  
    /**
     * this test is actualy to verify that no content is removed by the 'pretty 
     * print', BUT the current 'pretty print' service removes invalid/unmatched tags.
     */
    @Test
    public void toString_prettyPrint_nothingRemoved_invalidHTML()
    {
//TODO: find a pretty print that does not remove any content        
//TODO: and reverse the assertion of this unit test
        
        String targetText = "some <body> text";
        Div implementation = new Div();
        PlainText plainText = new PlainText(targetText);
        implementation.add(plainText);
        
        implementation.setPrettyPrint(true);
        
        String actual = implementation.toString();
        
        // assert no wrapper tags were added
        assertTrue( !actual.contains("<body>") );
    }
}
