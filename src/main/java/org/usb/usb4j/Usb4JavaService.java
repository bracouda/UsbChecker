package org.usb.usb4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usb4java.javax.Services;

import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbHub;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Usb4JavaService {
    private final static Logger logger = LogManager.getLogger(Usb4JavaService.class);
    private static Services services;

    static {
        try {
            services = new Services();
        } catch (UsbException e) {
            logger.error(e);
        }
    }

    public Usb4JavaService() {
    }

    public List<UsbContainer> generateList(){
        UsbHub rootUsbHub = services.getRootUsbHub();
        List<UsbDevice> usbDevices = getUsbDevices(rootUsbHub);
        return usbDevices.stream().map(usbDevice -> new UsbContainer(usbDevice)).collect(Collectors.toList());
    }
    private List<UsbDevice> getUsbDevices(UsbHub usbHub) {
        List<UsbDevice> devices = new ArrayList<>();
        List<UsbDevice> attachedUsbDevices = usbHub.getAttachedUsbDevices();
        for (UsbDevice device : attachedUsbDevices) {
            devices.add(device);
            if (device.isUsbHub()) {
                devices.addAll(getUsbDevices((UsbHub) device));
            }
        }
        return devices;
    }

}

