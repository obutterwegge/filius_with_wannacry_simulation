package filius.software.smb;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.clientserver.ClientAnwendung;
import filius.software.system.InternetKnotenBetriebssystem;
import filius.software.transportschicht.Socket;
import filius.software.transportschicht.TCPSocket;

public class SMBClient extends ClientAnwendung {

    protected Socket socket = null;

    public SMBClient(InternetKnotenBetriebssystem internetKnotenBetriebssystem) {
        super();
        this.setSystemSoftware(internetKnotenBetriebssystem);
    }

    public void sendMessage(String ipAddress, String publicKey) {
        Main.debug.println("Der SMBClient versucht eine Nachricht an " + ipAddress + " zu senden");
        try {
            socket = new TCPSocket(this.getSystemSoftware(), ipAddress, 33099);
            deliverMessage(new SMBMessage(publicKey));
        } catch (VerbindungsException verbindungsException) {
            verbindungsException.printStackTrace();
        }
    }

    public void deliverMessage(SMBMessage smbMessage) {
        try {
            if (!socket.istVerbunden()) {
                initializeSocket();
            }
            socket.senden(smbMessage.message);
            closeSocket();
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }

    public void initializeSocket() {
        try {
            socket.verbinden();
            Main.debug.println("Es wurde erfolgreich eine Verbindung hergestellt");
            benachrichtigeBeobachter("Verbindung Hergestellt");
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
            benachrichtigeBeobachter("Verbindung konnte nicht hergestellt werden");
        }

    }

    public void closeSocket() {
        if (socket.istVerbunden()) {
            socket.beenden();
        }
        Main.debug.println("Die Verbindung wurde erfolgreich geschlossen");
    }
}
