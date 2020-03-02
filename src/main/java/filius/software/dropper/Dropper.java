package filius.software.dropper;

import filius.Main;
import filius.exception.VerbindungsException;
import filius.software.Anwendung;
import filius.software.clientserver.ClientAnwendung;
import filius.software.transportschicht.TCPSocket;
import filius.software.transportschicht.UDPSocket;

/**
 * @author Oliver Butterwegge
 * This class is part of the WannaCry Visualization and install on the System the Ransomware
 */
public class Dropper extends ClientAnwendung {

    public Dropper(){
        try {
            TCPSocket tcpSocket = new TCPSocket(getSystemSoftware(), 30038);
            if(tcpSocket.istVerbunden()){
                String request = tcpSocket.empfangen();
                if (request.contains("install")){
                    installWannaCry();
                    runWannaCry();
                }
            }
        } catch (VerbindungsException e) {
            e.printStackTrace(Main.debug);
        }
    }
    /**
     * Install the Ransomware to the System
     */
    public void installWannaCry(){
        getSystemSoftware().installiereSoftware("Ransomware");
    }

    /**
     * Run the Encryption-part of the Ransomware
     */
    public void runWannaCry(){
        for (Anwendung application :
                getSystemSoftware().holeArrayInstallierteSoftware()) {
            if ("Ransomware".equals(application.holeAnwendungsName())){
                application.start();
            }
        }
    }
}
