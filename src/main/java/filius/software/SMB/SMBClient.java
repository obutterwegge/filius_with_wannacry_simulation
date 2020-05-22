package filius.software.SMB;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.clientserver.ClientAnwendung;
import filius.software.system.InternetKnotenBetriebssystem;
import filius.software.transportschicht.Socket;
import filius.software.transportschicht.TCPSocket;

import java.security.PublicKey;

public class SMBClient extends ClientAnwendung {

    protected Socket socket = null;

    public SMBClient(InternetKnotenBetriebssystem internetKnotenBetriebssystem) {
        super();
        this.setSystemSoftware(internetKnotenBetriebssystem);
    }

    public void sendMessage(String ipAddress, PublicKey publicKey) {
        System.out.println("Der SMBClient versucht eine Nachricht an " + ipAddress + " zu senden");
        try {
            socket = new TCPSocket(this.getSystemSoftware(), ipAddress, 33099);
            Object[] args;
            args = new Object[1];
            SMBMessage smbMessage = new SMBMessage(publicKey.toString());
            args[0] = smbMessage;
            ausfuehren("deliverMessage", args);
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
            System.out.println("Es wurde erfolgreich eine Verbindung hergestellt");
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
        System.out.println("Die Verbindung wurde erfolgreich geschlossen");
    }
}
