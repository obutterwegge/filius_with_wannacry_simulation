package filius.software.eternalblue;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.Anwendung;
import filius.software.system.InternetKnotenBetriebssystem;
import filius.software.transportschicht.TCPSocket;

import java.security.PublicKey;

public class EternalBlue extends Anwendung {

    public EternalBlue(InternetKnotenBetriebssystem internetKnotenBetriebssystem) {
        super();
        this.setSystemSoftware(internetKnotenBetriebssystem);
    }

    /**
     * try to infect the system with the given ipAdress
     * @param ipAdress The IP of the Victim
     * @param publicKey
     */
	public void infect(String ipAdress, PublicKey publicKey) {
        try {
            TCPSocket tcpSocket = new TCPSocket(this.getSystemSoftware(), ipAdress, 30038);
            if (tcpSocket.istVerbunden())
                tcpSocket.senden("install <publickey>"+publicKey.toString()+"</publickey>");
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }
}
