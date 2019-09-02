
package org.onebeartoe.application.ui;

import java.io.Serializable;

/**
 * This class represents a typical state a graphical user interface
 * can be in. Information such as size and location are stored in objects of
 * this class.
 *
 * @author Roberto H. Marquez
 */
public abstract class GUIConfig implements Serializable 
{
    private String configPath;

    private Integer windowX;
    private Integer windowY;

    private Integer windowWidth;
    private Integer windowHeight;

    /**
     * empty cnstructor for XML encodeing and XML decoding
     */
    public GUIConfig() {
    }

    public GUIConfig(Integer x, Integer y, Integer w, Integer h) {
        windowX = x;
        windowY = y;

        windowWidth = w;
        windowHeight = h;
    }

    public String getConfigPath() 
    {
        return configPath;
    }

    public Integer getWindowHeight() {
        return windowHeight;
    }

    public Integer getWindowWidth() {
        return windowWidth;
    }

    public Integer getWindowX() {
        return windowX;
    }

    public Integer getWindowY() {
        return windowY;
    }

    public void setWindowWidth(Integer w) {
        windowWidth = w;
    }

    public void setWindowHeight(Integer h) {
        windowHeight = h;
    }

    public void setWindowX(Integer x) {
        windowX = x;
    }

    public void setWindowY(Integer y) {
        windowY = y;
    }

}
