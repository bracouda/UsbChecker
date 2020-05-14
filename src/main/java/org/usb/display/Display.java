package org.usb.display;

import javax.swing.*;

public class Display extends JOptionPane {
    public static void showError(String title,String message){
        JTextArea ta = new JTextArea(10, 10);
        ta.setText(message);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setCaretPosition(0);
        ta.setEditable(false);
        showMessageDialog(null, ta, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInformation(String message){
        showMessageDialog(null,message,"Usb securityInformation",JOptionPane.INFORMATION_MESSAGE);
    }

}
