package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.swing.JOptionPane;

public class PinModel 
{
    private int kattDb = 0;
    private String pin = "";

    public void szamjegyHozzaad(String szamjegy) 
    {
        if (kattDb < 4) 
        {
            pin += szamjegy;
            kattDb++;
        }
    }

    public boolean keszVanE() 
    {
        return kattDb == 4;
    }

    public String getPin() 
    {
        return pin;
    }

    public void visszaallit() 
    {
        kattDb = 0;
        pin = "";
    }
    
    public void fajlbaKiir(String pin) 
    {
        Path path = Path.of("pinKod.txt");
        try 
        {
            Files.writeString(path, pin + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
