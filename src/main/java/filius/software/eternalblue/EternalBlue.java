package filius.software.eternalblue;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.Anwendung;
import filius.software.transportschicht.TCPSocket;

public class EternalBlue extends Anwendung {

    /**
     * try to infect the system with the given ipAdress
     * @param ipAdress The IP of the Victim
     */
	public void infect(String ipAdress) {
        try {
            TCPSocket tcpSocket = new TCPSocket(this.getSystemSoftware(), ipAdress, 30038);
            if (tcpSocket.istVerbunden())
                tcpSocket.senden("install");
        } catch (VerbindungsException e) {
            e.printStackTrace(Main.debug);
        } catch (TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
	}
}
