package org.usb.settings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.usb.display.Display;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonnalSettings {
    private static final String PATH = System.getProperty("user.home")+ File.separator+"security"+File.separator;
    private static final Logger logger = LogManager.getLogger(PersonnalSettings.class);

    private Properties properties;
    private PersonnalSettingsSource lastPersonnalSettingsSource;

    public PersonnalSettings(){
        properties = new Properties();
    }

    public void reloadFile(){
        PersonnalSettingsSource actualPersonnalSettingsSource = null;
        try (FileInputStream fileInputStream = new FileInputStream(PATH + "usb.properties")) {
            properties.load(fileInputStream);
            actualPersonnalSettingsSource = PersonnalSettingsSource.FILE;
        } catch (IOException e) {
            logger.error(e);
            try {
                properties.load(PersonnalSettings.class.getClassLoader().getResourceAsStream("usb.properties"));
                actualPersonnalSettingsSource = PersonnalSettingsSource.CLASSLOADER;
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        if(actualPersonnalSettingsSource!=lastPersonnalSettingsSource){
            Display.displayInformation("Personnal settings has been initialized with "+actualPersonnalSettingsSource);
            lastPersonnalSettingsSource = actualPersonnalSettingsSource;
        }
    }

    public Integer getNbExpected(){
        return Integer.valueOf(properties.getProperty("nbexpected"));
    }
    public Set<String> getWhiteList(){
        String[] listUsbDevices = properties.getProperty("listUsbDevice").split(";");
        return Arrays.stream(listUsbDevices).collect(Collectors.toSet());
    }
}