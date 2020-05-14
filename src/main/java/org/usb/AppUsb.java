package org.usb;

import java.util.Timer;

public class AppUsb
{

    public static void main(String[] args) {
        Timer t = new Timer();
        UsbTask mTask = new UsbTask();
        // This task is scheduled to run every 10 seconds
        t.scheduleAtFixedRate(mTask, 0, 10000);
    }

}
