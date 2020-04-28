package filius.gui.anwendungssicht;

import javax.swing.*;

import filius.software.ransomware.Ransomware;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;

/**
 * This GUI shows up, when the Ransomware is done with the encryption of the Files
 */
public class GUIApplicationRansomware extends GUIApplicationWindow {

    private static final long serialVersionUID = 1L;
    private JLabel lockImg;
    private JLabel raiseLabel;
    private JLabel endLabel;
    private JButton purchaseButton;
    private JButton decryptButton;
    private JTextField privateKeyField;
    private JTextArea InformationField;

    GUIApplicationRansomware(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        setVisible(false);
        ((Ransomware) holeAnwendung()).hinzuBeobachter(this);
        initComponents();
    }

    private void initComponents() {
        //construct components
        lockImg = new JLabel ("Lock");
        raiseLabel = new JLabel ("Raise");
        endLabel = new JLabel ("no encryption");
        purchaseButton = new JButton ("purchase");
        decryptButton = new JButton ("decrypt");
        privateKeyField = new JTextField (5);
        InformationField = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (801, 497));
        setLayout (null);
        setBackground(Color.MAGENTA);

        //add components
        add (lockImg);
        add (raiseLabel);
        add (endLabel);
        add (purchaseButton);
        add (decryptButton);
        add (privateKeyField);
        add (InformationField);

        //set component bounds (only needed by Absolute Positioning)
        lockImg.setBounds (30, 15, 165, 135);
        raiseLabel.setBounds (30, 160, 165, 135);
        endLabel.setBounds (30, 340, 165, 135);
        purchaseButton.setBounds (355, 430, 100, 25);
        decryptButton.setBounds (645, 430, 100, 25);
        privateKeyField.setBounds (360, 380, 385, 30);
        InformationField.setBounds (360, 15, 385, 335);

    }

    @Override
    public void update(Observable observable, Object o) {
        setVisible(true);
    }
}
