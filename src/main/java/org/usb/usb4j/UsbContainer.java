package org.usb.usb4j;

import javax.usb.UsbDevice;

public class UsbContainer {
    private final String idVendor;
    private final String idProduct;

    public UsbContainer(UsbDevice usbDevice) {
        this.idProduct = String.format("%04x", usbDevice.getUsbDeviceDescriptor().idProduct());
        this.idVendor = String.format("%04x", usbDevice.getUsbDeviceDescriptor().idVendor());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(idVendor);
        builder.append(":");
        builder.append(idProduct);
        return builder.toString();
    }
}