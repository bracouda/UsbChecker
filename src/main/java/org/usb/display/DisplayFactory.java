package org.usb.display;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usb.display.impl.DisplayPane;
import org.usb.display.impl.SystemTrayDisplay;
import org.usb.display.impl.TerminalDisplay;

public class DisplayFactory {

    private static final Logger logger = LogManager.getLogger(DisplayPane.class);

    public static IDisplay getDisplay(){
        SystemTrayDisplay systemtrayDisplay = new SystemTrayDisplay();
        if(systemtrayDisplay.isAvailable()){
            logger.info("SystemTray impl selected");
            return systemtrayDisplay;
        }
        DisplayPane displayPane = new DisplayPane();
        if(displayPane.isAvailable()){
            logger.info("DisplayPane impl selected");
            return displayPane;
        }
        logger.info("Terminal impl by default selected");
        return new TerminalDisplay();
    }
}
