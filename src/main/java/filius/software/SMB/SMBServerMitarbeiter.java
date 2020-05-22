package filius.software.SMB;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("Es ist eine Nachricht eingetroffen");
        System.out.println("Ihr Inhalt ist");
        System.out.println(nachricht);
        installDropper(nachricht);
        try {
            socket.senden("Done");
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }

    private void installDropper(String publicKeyString) {
        Dropper dropper = new Dropper(getKey(publicKeyString), this.server.getSystemSoftware());
        System.out.println("Dropper wurde installiert und startet");
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
