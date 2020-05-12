package filius.gui.anwendungssicht;

import filius.software.wannacry.WannaCry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class GUIApplicationWannaCry extends GUIApplicationWindow{

    private static final long serialVersionUID = 1L;
    private JPanel backpanel;

    public GUIApplicationWannaCry(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        initComponents();
        this.getContentPane().add(backpanel);
        pack();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("WannaCry Starter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.append("When you click the start button in the background the hole process starts this means\n");
        descriptionArea.append("1. The Dropper starts to infect this system \n");
        descriptionArea.append("2. The Ransomware will encrypt the files on this systems \n");
        descriptionArea.append("3. The Dropper starts to guess other available Systems in the network and use Eternalblue to infect them");
        JButton startButton = new JButton("Start the WannaCry Attack");
        startButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                ((WannaCry) holeAnwendung()).starteWannaCryAttack();
            }
        });
        backpanel = new JPanel();
        Box box = Box.createVerticalBox();
        box.add(titleLabel);
        box.add(descriptionArea);
        box.add(startButton);
        backpanel.add(box, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
