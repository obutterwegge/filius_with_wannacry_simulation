package filius.software.ransomware;

import filius.Main;
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

    private PublicKey publicKey;

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void starten() {
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
                e.printStackTrace(Main.debug);
            }
        }
        this.benachrichtigeBeobachter();
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
                e.printStackTrace(Main.debug);
            }
        }
    }
}
