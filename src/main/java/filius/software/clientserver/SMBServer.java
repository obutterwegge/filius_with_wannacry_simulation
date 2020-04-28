package filius.software.clientserver;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.rahmenprogramm.Base64;
import filius.software.Anwendung;
import filius.software.clientserver.ClientAnwendung;
import filius.software.dropper.Dropper;
import filius.software.transportschicht.TCPSocket;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class SMBServer extends ClientAnwendung {

    @Override
    public void starten() {
        try {
            TCPSocket tcpSocket = new TCPSocket(getSystemSoftware(), 30038);
            if (tcpSocket.istVerbunden()) {
                String request = tcpSocket.empfangen();
                if (request.contains("install")) {
                    String[] Message = request.split("<publickey>", 2);
                    installDropper(Message[1]);
                }
            }
        } catch (VerbindungsException | TimeOutException e) {
            e.printStackTrace(Main.debug);
        }
    }

    private void installDropper(String publicKeyString) {
        PublicKey publicKey = getKey(publicKeyString);
        Dropper dropper = new Dropper(publicKey, this.getSystemSoftware());
        dropper.starten();
    }

    public static PublicKey getKey(String key){
        try{
            byte[] byteKey = Base64.decode(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    


}
