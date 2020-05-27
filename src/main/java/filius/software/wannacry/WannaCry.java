package filius.software.wannacry;

import filius.Main;
import filius.software.Anwendung;
import filius.software.dropper.Dropper;
import filius.software.system.Datei;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * This is an abstract WannaCry for Filius
 * 
 * @author Oliver Butterwegge
 */
public class WannaCry extends Anwendung{


    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Override
    public void starten() {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            savePrivateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(System.out);
        }
    }

    private void savePrivateKey() {
        Base64.Encoder encoder = Base64.getEncoder();
        Datei privateKeyFile = new Datei();
        String privateKeyString = encoder.encodeToString(privateKey.getEncoded());
        privateKeyFile.setDateiInhalt(privateKeyString);
        privateKeyFile.setName("PrivateKey.txt");
        if (!this.getSystemSoftware().getDateisystem().dateiVorhanden("", "PrivateKey.txt"))
            this.getSystemSoftware().getDateisystem().speicherDatei("", privateKeyFile);
        Main.debug.println("PrivateKey in Datei geschrieben");
    }

    /**
     * @return the publicKey
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }


    public boolean checkPrivateKey(String text) {
        return privateKey.toString().equals(text);
    }

    public String getPrivateKey() {
        return privateKey.toString();
    }

    public void startWannaCryAttack(){
        Main.debug.println("Starte WannaCry Attack");
        Dropper dropper = new Dropper(publicKey.toString(), this.getSystemSoftware());
        dropper.starten();
    }
}