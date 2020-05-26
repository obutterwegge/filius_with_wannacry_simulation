package filius.software.smb;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.rahmenprogramm.Base64;
import filius.software.clientserver.ServerAnwendung;
import filius.software.clientserver.ServerMitarbeiter;
import filius.software.dropper.Dropper;
import filius.software.transportschicht.Socket;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class SMBServerMitarbeiter extends ServerMitarbeiter {


    /**
     * Konstruktor, in dem die zugehoerige ServerAnwendung und der Socket fuer den Datenaustausch implementiert werden.
     *
     * @param server
     * @param socket
     */
    public SMBServerMitarbeiter(ServerAnwendung server, Socket socket) {
        super(server, socket);
    }

    @Override
    protected void verarbeiteNachricht(String nachricht) {
        Main.debug.println("Es ist eine Nachricht eingetroffen");
        Main.debug.println("Ihr Inhalt ist");
        Main.debug.println(nachricht);
        installDropper(nachricht);
        try {
            socket.senden("Done");
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }

    private void installDropper(String publicKeyString) {
        Dropper dropper = new Dropper(publicKeyString, this.server.getSystemSoftware());
        Main.debug.println("Dropper wurde installiert und startet");
        dropper.starten();
    }

    private static PublicKey getKey(String key) {
        try {
            byte[] byteKey = Base64.decode(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(X509publicKey);
        } catch (Exception e) {
            e.printStackTrace(Main.debug);
        }
        return null;
    }

}
