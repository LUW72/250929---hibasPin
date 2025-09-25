package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PinModel 
{
    private static int kattDb = 0;
    private static boolean mentve = false;
    private static String pin = "";

    public PinModel() 
    {
        
    }

    public void megnyitasra(JPanel jPanel1, JCheckBox chbMutat, javax.swing.RootPaneContainer rootPane) 
    {
        for (int i = 0; i < jPanel1.getComponentCount(); i++) 
        {
            JButton btn = (JButton) jPanel1.getComponent(i);
            btn.setText(i + "");
            btn.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if(kattDb <= 4) 
                    {
                        kattDb++;
                        pin += e.getActionCommand();
                    } 
                    if(kattDb == 4) 
                    {
                        chbMutat.setEnabled(true);
                        fajlbaKiir(pin, rootPane);
                        JOptionPane.showMessageDialog(rootPane.getContentPane(), "Pin mentve!");
                    }
                    
                }
            });
        }
    }
    
    public void checkBoxKodMegmutat(JPanel jPanel1, JCheckBox chbMutat)
    {
        if(chbMutat.isSelected()){
            for (int i = 0; i < pin.length(); i++) 
            {
                int gomb = Integer.parseInt(pin.charAt(i)+"");
                jPanel1.getComponent(gomb).setBackground(Color.red);
            }
        }
        else
        {
            chbMutat.setEnabled(false);
            kattDb = 0;
            for (int i = 0; i < pin.length(); i++) 
            {
                int gomb = Integer.parseInt(pin.charAt(i)+"");
                jPanel1.getComponent(gomb).setBackground(Color.LIGHT_GRAY);
            }
        }
    }
    
    private void fajlbaKiir(String pin, javax.swing.RootPaneContainer rootPane) 
    {
        Path path = Path.of("pinKod.txt");
        try 
        {
            Files.writeString(path, pin + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(rootPane.getContentPane(), e.getMessage());
        }
    }
    
}
