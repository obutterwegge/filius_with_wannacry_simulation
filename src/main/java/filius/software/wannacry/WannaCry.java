package filius.software.wannacry;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedList;

import filius.Main;
import filius.software.Anwendung;
import filius.software.dropper.Dropper;
import filius.software.system.Datei;
import filius.software.system.Dateisystem;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
            e.printStackTrace(Main.debug);
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

    public void starteWannaCryAttack(){
        Dropper dropper = new Dropper(publicKey, this.getSystemSoftware());
        dropper.starten();
    }
}