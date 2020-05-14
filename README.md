# USB CHECKER

## What it does ?
Usb checker warn you if an unknown usb device is connected to your computer

## But why ?

All started from joke from colleagues who plug another keyboard to my computer and do some magic when i'm was on it :)

## How to configure ?
you just need to have a property file who contain
number of devices and which devices.

- mac
> /Users/<username>/security/usb.properties 
- windows
> %USERHOME%/security/usb.properties

the properties file should contains 2 properties 
```
nbexpected = 0
listUsbDevice =;
```
## How to start ?

- windows
```
start.bat
```
- unix
```
./start.sh
```

