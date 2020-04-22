package filius.software.system;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.Anwendung;
import filius.software.transportschicht.TCPSocket;

public class SMBServer extends Anwendung {

    @Override
    public void starten() {
        try {
            TCPSocket tcpSocket = new TCPSocket(getSystemSoftware(), 30038);
            if (tcpSocket.istVerbunden()) {
                String request = tcpSocket.empfangen();
                if (request.contains("install")) {
                    installDropper();
                }
            }
        } catch (VerbindungsException e) {
            e.printStackTrace(Main.debug);
        } catch (TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }

    private void installDropper() {
        this.getSystemSoftware().installiereSoftware("Dropper");
    }
    


}
