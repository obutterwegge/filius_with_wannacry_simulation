package filius.gui.anwendungssicht;

import filius.software.lokal.ImageViewer;
import filius.software.ransomware.Ransomware;
import filius.software.system.Datei;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class GUIApplicationRansomware extends GUIApplicationWindow{

    private JPanel ransomwarePanel;
    Datei privateKey;
    Ransomware ransomware;

    private javax.swing.JButton decryptButton;
    private javax.swing.JPanel decryptPanel;
    private javax.swing.JLabel goneCountdownLabel;
    private javax.swing.JLabel goneLabel;
    private javax.swing.JPanel gonePanel;
    private javax.swing.JScrollPane informationPane;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JTextArea informationTextArea;
    private javax.swing.JLabel lockImage;
    private javax.swing.JPanel lockPanel;
    private javax.swing.JTextField privateKeyTextField;
    private javax.swing.JButton purchaseButton;
    private javax.swing.JPanel raisePanel;
    private javax.swing.JLabel raisingCountdownLabel;
    private javax.swing.JLabel raisingLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;

    public GUIApplicationRansomware(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        ransomware = ((Ransomware) holeAnwendung());
        privateKey = ransomware.getSystemSoftware().getDateisystem().holeDatei("","PrivateKey.txt");
        initComponents();
        this.getContentPane().add(ransomwarePanel);
        pack();
    }

    //This component was created by Netbeans IDE
    private void initComponents() {

        ransomwarePanel = new JPanel();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        lockPanel = new javax.swing.JPanel();
        lockImage = new javax.swing.JLabel();
        raisePanel = new javax.swing.JPanel();
        raisingLabel = new javax.swing.JLabel();
        raisingCountdownLabel = new javax.swing.JLabel();
        gonePanel = new javax.swing.JPanel();
        goneLabel = new javax.swing.JLabel();
        goneCountdownLabel = new javax.swing.JLabel();
        informationPanel = new javax.swing.JPanel();
        informationPane = new javax.swing.JScrollPane();
        informationTextArea = new javax.swing.JTextArea();
        decryptPanel = new javax.swing.JPanel();
        privateKeyTextField = new javax.swing.JTextField();
        purchaseButton = new javax.swing.JButton();
        decryptButton = new javax.swing.JButton();

        ransomwarePanel.setBackground(new java.awt.Color(204, 0, 51));

        titlePanel.setBackground(new java.awt.Color(204, 0, 51));
        titlePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        titleLabel.setBackground(new java.awt.Color(204, 0, 51));
        titleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("WanaDecrypt0r");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                                .addContainerGap(362, Short.MAX_VALUE)
                                .addComponent(titleLabel)
                                .addGap(228, 228, 228))
        );
        titlePanelLayout.setVerticalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(titlePanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(titleLabel)
                                .addContainerGap(65, Short.MAX_VALUE))
        );

        lockPanel.setBackground(new java.awt.Color(204, 0, 51));
        lockPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lockPanel.setForeground(new java.awt.Color(255, 255, 255));


        ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/img/lock_icon.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        lockImage.setIcon(imageIcon);

        javax.swing.GroupLayout lockPanelLayout = new javax.swing.GroupLayout(lockPanel);
        lockPanel.setLayout(lockPanelLayout);
        lockPanelLayout.setHorizontalGroup(
                lockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lockPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lockImage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lockPanelLayout.setVerticalGroup(
                lockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lockPanelLayout.createSequentialGroup()
                                .addComponent(lockImage)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        raisePanel.setBackground(new java.awt.Color(204, 0, 51));
        raisePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        raisingLabel.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        raisingLabel.setForeground(new java.awt.Color(255, 255, 255));
        raisingLabel.setText("The Price is raising in");

        raisingCountdownLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        raisingCountdownLabel.setForeground(new java.awt.Color(255, 255, 255));
        raisingCountdownLabel.setText("00:00:00");

        javax.swing.GroupLayout raisePanelLayout = new javax.swing.GroupLayout(raisePanel);
        raisePanel.setLayout(raisePanelLayout);
        raisePanelLayout.setHorizontalGroup(
                raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(raisePanelLayout.createSequentialGroup()
                                .addGroup(raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(raisePanelLayout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(raisingLabel))
                                        .addGroup(raisePanelLayout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(raisingCountdownLabel)))
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        raisePanelLayout.setVerticalGroup(
                raisePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(raisePanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(raisingLabel)
                                .addGap(32, 32, 32)
                                .addComponent(raisingCountdownLabel)
                                .addContainerGap(64, Short.MAX_VALUE))
        );

        gonePanel.setBackground(new java.awt.Color(204, 0, 51));
        gonePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        goneLabel.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        goneLabel.setForeground(new java.awt.Color(255, 255, 255));
        goneLabel.setText("The Files are gone in");

        goneCountdownLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        goneCountdownLabel.setForeground(new java.awt.Color(255, 255, 255));
        goneCountdownLabel.setText("00:00:00");

        javax.swing.GroupLayout gonePanelLayout = new javax.swing.GroupLayout(gonePanel);
        gonePanel.setLayout(gonePanelLayout);
        gonePanelLayout.setHorizontalGroup(
                gonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(gonePanelLayout.createSequentialGroup()
                                .addGroup(gonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(gonePanelLayout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(goneLabel))
                                        .addGroup(gonePanelLayout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(goneCountdownLabel)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gonePanelLayout.setVerticalGroup(
                gonePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(gonePanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(goneLabel)
                                .addGap(39, 39, 39)
                                .addComponent(goneCountdownLabel)
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        informationPanel.setBackground(new java.awt.Color(204, 0, 51));
        informationPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        informationTextArea.setColumns(20);
        informationTextArea.setRows(5);
        informationTextArea.setEditable(false);
        informationTextArea.append("What Happened to My Computer?\n");
        informationTextArea.append("Your important files are encrypted\n");
        informationTextArea.append("Many of your documents, photos, videos, databases and other files are no longer accessible\n because they have been encrypted. Maybe you are busy looking for a way to recover your files, \n but do not waste your time. Nobody can revocer your files withour our decryption service.\n\n");
        informationTextArea.append("Can I Recover My Files?\n");
        informationTextArea.append("Sure. We guarantee that you can recover all your files afely and easily. But you have not so enough time.\n");
        informationTextArea.append("You can decrypt some of your files for free. Try now by clicking <Decrypt>\n");
        informationTextArea.append("But if you want to decrypt all your files, you need to pay\n");
        informationTextArea.append("You only have 3 days to submit the payment. After that the price will doubled.\n");
        informationTextArea.append("Also, if you don't pay in 7 days, you won't be abe to recover your files forever.\n");
        informationTextArea.append("We will have free events for users who are so poor that they couldn't pay in 6 monts.\n\n");
        informationTextArea.append("How do I Pay?\n");
        informationTextArea.append("Here would come a description, what is Bitcoin and how you can get some to Pay.\n");
        informationPane.setViewportView(informationTextArea);

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
                informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(informationPane)
        );
        informationPanelLayout.setVerticalGroup(
                informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(informationPane)
        );

        decryptPanel.setBackground(new java.awt.Color(204, 0, 51));
        decryptPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        privateKeyTextField.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        privateKeyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        privateKeyTextField.setText("PrivateKey");

        purchaseButton.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        purchaseButton.setText("Purchase");
        purchaseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showInputDialog(privateKey.getDateiInhalt());
            }
        });

        decryptButton.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        decryptButton.setText("Decrypt");
        decryptButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ransomware.decryptDataAsyncrhon(privateKeyTextField.getText());
            }
        });

        javax.swing.GroupLayout decryptPanelLayout = new javax.swing.GroupLayout(decryptPanel);
        decryptPanel.setLayout(decryptPanelLayout);
        decryptPanelLayout.setHorizontalGroup(
                decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(decryptPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(privateKeyTextField)
                                        .addGroup(decryptPanelLayout.createSequentialGroup()
                                                .addComponent(purchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(decryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        decryptPanelLayout.setVerticalGroup(
                decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(decryptPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(privateKeyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(decryptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(purchaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(decryptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(ransomwarePanel);
        ransomwarePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(lockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(gonePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(raisePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(informationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(decryptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(raisePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(informationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(gonePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(decryptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
    }

    @Override
    public void update(Observable o, Object arg) {
        setVisible(true);
    }
}
