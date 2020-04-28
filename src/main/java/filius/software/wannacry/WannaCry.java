package filius.software.wannacry;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import filius.Main;
import filius.software.Anwendung;
import filius.software.dropper.Dropper;
import filius.software.system.Datei;

/**
 * This is an abstract WannaCry for Filius
 * 
 * @author Oliver Butterwegge
 */
public class WannaCry extends Anwendung{


    private PublicKey publicKey;
    private PrivateKey privateKey;
    private Dropper dropper;

    @Override
    public void starten() {
        /*
        When installed, WannaCry just inizialized the Dropper
        */
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            savePrivateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(Main.debug);
        }
        dropper = new Dropper(publicKey, this.getSystemSoftware());
    }

    private void savePrivateKey() {
        Datei privateKeyFile = new Datei();
        privateKeyFile.setDateiInhalt(this.privateKey.toString());
        this.getSystemSoftware().getDateisystem().speicherDatei("", privateKeyFile);
    }

    public void startDropper(){
        dropper.starten();
    }

    public void stopDropper(){
        dropper.beenden();
    }

    /**
     * @return the publicKey
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }
}