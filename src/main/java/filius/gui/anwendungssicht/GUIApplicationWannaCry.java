package filius.gui.anwendungssicht;

import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;


public class GUIApplicationWannaCry extends GUIApplicationWindow {

    private static final long serialVersionUID = 1L;
    private JPanel wannaCryPanel;
    private JTextField privateKeyTextField;
    private JButton startButton, stopButton;

    public GUIApplicationWannaCry(GUIDesktopPanel desktop, String appKlasse) {
        super(desktop, appKlasse);
        initComponents();
    }

    private void initComponents() {
        wannaCryPanel = new JPanel();
        setLayout(new BorderLayout());
        privateKeyTextField = new JTextField();
        startButton = new JButton();
        stopButton = new JButton();
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

}