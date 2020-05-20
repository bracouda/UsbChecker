package org.usb.display.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usb.display.IDisplay;

import java.awt.*;

public class SystemTrayDisplay implements IDisplay {

    private static final Logger logger = LogManager.getLogger(SystemTrayDisplay.class);
    private TrayIcon trayIcon;
    public SystemTrayDisplay(){
        if(!isAvailable()){
            throw new UnsupportedOperationException();
        }
        Image image = Toolkit.getDefaultToolkit().getImage("gifIcon.gif");

        trayIcon = new TrayIcon(image,"UsbChecker");
        trayIcon.setImageAutoSize(true);
        try{
            SystemTray.getSystemTray().add(trayIcon);
        }catch (AWTException e){
            logger.error(e);
        }
    }
    @Override
    public void showError(String title, String message) {
        if(!isAvailable()){
            throw new UnsupportedOperationException();
        }
        trayIcon.displayMessage(title,message, TrayIcon.MessageType.ERROR);
    }

    @Override
    public void displayInformation(String message) {
        if(!isAvailable()){
            throw new UnsupportedOperationException();
        }
        trayIcon.displayMessage("Information",message, TrayIcon.MessageType.INFO);

    }

    @Override
    public boolean isAvailable() {
        return SystemTray.isSupported();
    }
}
