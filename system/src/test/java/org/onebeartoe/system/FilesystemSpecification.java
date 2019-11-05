/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onebeartoe.system;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class tests the FileSystem specification.
 */
public class FilesystemSpecification
{
    private Filesystem implementation;
    
    @BeforeTest
    public void setUpMethod() throws Exception
    {
        implementation = new Filesystem();
    }

    /**
     * Test of pwd method, of class Filesystem.
     */
    @Test
    public void pwd()
    {
        String pwd = implementation.pwd();
        
        assertNotNull(pwd);
        
        assertFalse( pwd.isEmpty() );
    }

    /**
     * Test of systimeToFilename method, of class Filesystem.
     */
    @Test
    public void systimeToFilename()
    {
        String filename = implementation.systimeToFilename();
        
        assertNotNull(filename);
        
        assertFalse(filename.isEmpty() );        
    }
    
}
