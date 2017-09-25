
package org.onebeartoe.application.ui.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;

public class ScrollableTextArea extends JScrollPane 
{
    static final long serialVersionUID = 3L;

    private Font font;

    private JTextArea text;

    private Color textareaForeground;
    
    private Color textareaBackground;

    public ScrollableTextArea(String default_str)
    {
        text = new JTextArea(default_str);
        setViewportView(text);
    }
    
    public void addTextListener(DocumentListener listener)
    {
        text.getDocument()
            .addDocumentListener(listener);
    }
    
    public String getText()
    {
        return text.getText();
    }

    public void setLineWrap(boolean wrapped)
    {
            text.setLineWrap(wrapped);
    }

    public void setText(String str) 
    {

            text.setText(str);
    }

    public void appendText(String str) 
    {
            text.append(str);
    }

    public void setTextAreaFont(Font f) 
    {
            if(f != null) {
                    text.setFont(f);
            }
    }

    public Font getFont() 
    {
            return font;
    }

    public Color getTextareaForeground() {
            return textareaForeground;
    }

    public void setTextareaForeground(Color textareaForeground) 
    {
            text.setForeground(textareaForeground);
    }

    public Color getTextareaBackground() {
            return textareaBackground;
    }

    public void setTextareaBackground(Color textareaBackground) 
    {
            text.setBackground(textareaBackground);
    }	
}
