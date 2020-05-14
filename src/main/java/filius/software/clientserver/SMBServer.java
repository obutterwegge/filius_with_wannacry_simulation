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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMBServer extends ClientAnwendung {


    private boolean running = false;

    @Override
    public void starten() {
        running = true;
        ausfuehren("listen", null);
    }

    public void listen(){
        while (running){
            try {
                TCPSocket tcpSocket = new TCPSocket(getSystemSoftware(), 30038);
                if (tcpSocket.istVerbunden()) {
                    String request = tcpSocket.empfangen();
                    if (request.contains("publickey")) {
                        final Pattern publickeyPattern = Pattern.compile("<publickey>(.+?)</publickey>");
                        final Matcher publickeyMatcher = publickeyPattern.matcher(request);
                        if(publickeyMatcher.find())
                        {
                            installDropper(publickeyMatcher.group(1));
                        }
                    }
                    tcpSocket.beenden();
                }
            } catch (VerbindungsException | TimeOutException e) {
                e.printStackTrace(Main.debug);
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private static PublicKey getKey(String key){
        try{
            byte[] byteKey = Base64.decode(key);
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            e.printStackTrace(Main.debug);
        }
        return null;
    }

    private void installDropper(String publicKeyString) {
        Dropper dropper = new Dropper(getKey(publicKeyString), this.getSystemSoftware());
        dropper.starten();
    }

}
