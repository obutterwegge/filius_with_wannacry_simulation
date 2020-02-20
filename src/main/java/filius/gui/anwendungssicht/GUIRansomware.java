package filius.gui.anwendungssicht;

import filius.software.ransomware.Ransomware;

import javax.swing.*;
import java.util.Observable;

public class GUIRansomware extends GUIApplicationWindow {

    private Ransomware ransomware;

    public GUIRansomware(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        ransomware = new Ransomware();
        initComponents();
    }

    private void initComponents() {
        Box box;
        JButton encryptButton, decryptButton;
        JLabel currentKeyLabel;
        JTextArea currentKey;
        encryptButton = new JButton("Encrypt Data");
        currentKey = new JTextArea();
        encryptButton.addActionListener(actionEvent -> {
            ransomware.starten();
            ransomware.encryptData();
            currentKey.setText(ransomware.getPrivateKey().toString());
        });
        decryptButton = new JButton("Decrypt Data");
        decryptButton.addActionListener(actionEvent -> {
            ransomware.decryptData();
        });
        currentKeyLabel = new JLabel("Current Private Key");
        box = Box.createHorizontalBox();
        box.add(encryptButton);
        box.add(decryptButton);
        box.add(currentKeyLabel);
        box.add(currentKey);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
