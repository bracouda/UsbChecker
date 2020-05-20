package org.usb;

import org.usb.display.DisplayFactory;
import org.usb.display.IDisplay;
import org.usb.error.UsbCountException;
import org.usb.error.UsbDoubleException;
import org.usb.error.UsbWhiteListException;
import org.usb.settings.PersonnalSettings;

import java.util.TimerTask;

public class UsbTask extends TimerTask {

    private final PersonnalSettings personnalSettings;
    private final IDisplay display;

    UsbTask() {
        personnalSettings = new PersonnalSettings();
        display = DisplayFactory.getDisplay();
    }

    @Override
    public void run() {
        this.personnalSettings.reloadFile();
        try {
            UsbOperator usbOperator = new UsbOperator(personnalSettings);
            usbOperator.checkCount();
            usbOperator.checkWhiteList();
            usbOperator.checkDoublon();
        } catch (UsbCountException e) {
            display.showError("Usb count error !",e.getMessage());
        } catch (UsbDoubleException e) {
            display.showError("Usb duplicate error !",e.getMessage());
        } catch (UsbWhiteListException e) {
            display.showError("Usb whiteList error !",e.getMessage());
        }

    }
}
