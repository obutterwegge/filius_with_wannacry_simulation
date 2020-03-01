package filius.gui.anwendungssicht;

import filius.software.ransomware.Ransomware;

import javax.swing.*;
import java.util.Observable;

/**
 * This GUI shows up, when the Ransomware is done with the encryption of the Files
 */
public class GUIApplicationRansomware extends GUIApplicationWindow {

    private Ransomware ransomware;

    public GUIApplicationRansomware(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        ransomware = new Ransomware();
        initComponents();
    }

    private void initComponents() {

        JButton encryptButton = new JButton("Encrypt Data");
        JTextArea currentKey = new JTextArea();
        encryptButton.addActionListener(actionEvent -> {
            ransomware.starten();
            ransomware.encryptData();
            currentKey.setText(ransomware.getPrivateKey().toString());
        });
        JButton decryptButton = new JButton("Decrypt Data");
        decryptButton.addActionListener(actionEvent -> ransomware.decryptData());
        JLabel currentKeyLabel = new JLabel("Current Private Key");
        Box box = Box.createHorizontalBox();
        box.add(encryptButton);
        box.add(decryptButton);
        box.add(currentKeyLabel);
        box.add(currentKey);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
