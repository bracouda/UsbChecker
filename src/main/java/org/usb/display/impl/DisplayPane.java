package org.usb.display.impl;

import org.usb.display.IDisplay;

import javax.swing.*;
import java.awt.*;

public class DisplayPane implements IDisplay {

    public void showError(String title,String message){
        if(!isAvailable()){
            throw new UnsupportedOperationException();
        }
        JTextArea ta = new JTextArea(10, 10);
        ta.setText(message);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setCaretPosition(0);
        ta.setEditable(false);
        JOptionPane.showMessageDialog(null, ta, title, JOptionPane.ERROR_MESSAGE);
    }

    public void displayInformation(String message){
        if(!isAvailable()){
            throw new UnsupportedOperationException();
        }
        JOptionPane.showMessageDialog(null,message,"Usb securityInformation",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean isAvailable() {
        return !GraphicsEnvironment.isHeadless();
    }

}
