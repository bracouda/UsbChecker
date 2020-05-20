package org.usb.display.impl;

import org.usb.display.IDisplay;

public class TerminalDisplay implements IDisplay {

    @Override
    public void showError(String title, String message) {
        System.out.println("/!\\ "+title+" /!\\ "+message);
    }

    @Override
    public void displayInformation(String message) {
        System.out.println(message);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
