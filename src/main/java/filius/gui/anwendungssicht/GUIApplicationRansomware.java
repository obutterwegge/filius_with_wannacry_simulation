package filius.gui.anwendungssicht;

import filius.software.ransomware.Ransomware;
import filius.software.system.Datei;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class GUIApplicationRansomware extends GUIApplicationWindow{

    private JPanel wannacryPanel;
    Datei privateKey;
    Ransomware ransomware;

    public GUIApplicationRansomware(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        ransomware = ((Ransomware) holeAnwendung());
        privateKey = ransomware.getSystemSoftware().getDateisystem().holeDatei("","PrivateKey.txt");
        initComponent();
        this.getContentPane().add(wannacryPanel);
        pack();
    }

    private void initComponent() {
        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        wannacryPanel = new JPanel(new BorderLayout());
        JLabel wannacryTitle = new JLabel("WannaCry Decrypt0r");
        JLabel lockIcon = new JLabel();
        JLabel raiseLable = new JLabel("Payment will be raised on");
        JLabel lostLable = new JLabel("Your files will be lost on");
        JLabel raiseCountdown = new JLabel("00:00:00");
        JLabel lostCountdown = new JLabel("00:00:00");
        JLabel payLabel = new JLabel("Pay 300€ and insert then the received PrivateKey");
        JTextField privateKeyField = new JTextField();
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showInputDialog(privateKey.getDateiInhalt());
            }
        });
        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ransomware.decryptData();
            }
        });
        JScrollPane informationScrollbar = new JScrollPane();
        JTextArea informationTextArea = new JTextArea("<html><div>When you click the start button in the background the hole process starts this means<br>" +
                "<ol>" +
                "<li>The Dropper starts to infect this system</li><li>The Ransomware will encrypt the files on this systems</li>" +
                "<li>The Dropper starts to guess other available Systems in the network and use Eternalblue to infect them</li>" +
                "</div></html>");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(purchaseButton);
        buttonPanel.add(decryptButton);
        informationScrollbar.add(informationTextArea);
        leftPanel.add(lockIcon);
        leftPanel.add(raiseLable);
        leftPanel.add(raiseCountdown);
        leftPanel.add(lostLable);
        leftPanel.add(lostCountdown);
        rightPanel.add(informationScrollbar);
        rightPanel.add(payLabel);
        rightPanel.add(privateKeyField);
        rightPanel.add(buttonPanel);
        wannacryPanel.add(wannacryTitle, BorderLayout.PAGE_START);
        wannacryPanel.add(leftPanel, BorderLayout.LINE_START);
        wannacryPanel.add(rightPanel, BorderLayout.LINE_END);
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(true);
    }
}
