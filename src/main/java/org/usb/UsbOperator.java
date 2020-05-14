package org.usb;

import org.apache.commons.lang3.StringUtils;
import org.usb.error.UsbCountException;
import org.usb.error.UsbDoubleException;
import org.usb.error.UsbWhiteListException;
import org.usb.settings.PersonnalSettings;
import org.usb.usb4j.Usb4JavaService;
import org.usb.usb4j.UsbContainer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsbOperator {
    private final PersonnalSettings personnalSettings;
    private final List<UsbContainer> usbContainers;

    public UsbOperator(PersonnalSettings personnalSettings) {
        this.personnalSettings = personnalSettings;
        this.usbContainers = new Usb4JavaService().generateList();
    }

    private boolean isDeviceIsNotKnown(UsbContainer usbDevice) {
        return !personnalSettings.getWhiteList().contains(usbDevice.toString());
    }

    public void checkWhiteList() throws UsbWhiteListException {
        List<UsbContainer> collect = usbContainers.stream().filter(this::isDeviceIsNotKnown).collect(Collectors.toList());
        if (collect.size() > 0) {
            String errorMessage = collect.stream()
                    .map(e -> e.toString())
                    .collect(Collectors.joining("\n"));
            throw new UsbWhiteListException(errorMessage);
        }
    }

    public void checkCount() throws UsbCountException {
        int count = usbContainers.size();
        if (!personnalSettings.getNbExpected().equals(count)) {
            throw new UsbCountException("Found " + count + " usb device instead of " + personnalSettings.getNbExpected() + "!");
        }
    }

    public void checkDoublon() throws UsbDoubleException {
        String errorMessage = usbContainers.stream()
                .filter(i -> Collections.frequency(usbContainers, i) > 1)
                .collect(Collectors.toSet()).stream()
                .map(e -> e.toString())
                .collect(Collectors.joining("\n"));
        if(StringUtils.isNotBlank(errorMessage)){
            throw new UsbDoubleException(errorMessage);
        }

    }

}
