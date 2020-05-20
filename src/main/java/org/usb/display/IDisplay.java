package org.usb.display;

public interface IDisplay {
    void showError(String title,String message);
    void displayInformation(String message);
    boolean isAvailable();
}
