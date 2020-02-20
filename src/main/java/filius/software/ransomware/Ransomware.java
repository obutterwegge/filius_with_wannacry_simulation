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

public class Ransomware extends Anwendung {

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
            e.printStackTrace();
        }
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void encryptData() {
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        //Wenn das gesuchte verzeichnis leer ist, liefert es root zurück, wenn ich dann in der Suche nach * müssten alle Dateien aufgelistet werden!
        LinkedList<Datei> linkedList = dateisystem.dateiSuche("", "*");
        for (Datei datei :
                linkedList) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                datei.setDateiInhalt(Arrays.toString(cipher.doFinal(datei.getDateiInhalt().getBytes())));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
    }

    public void decryptData() {
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        //Wenn das gesuchte verzeichnis leer ist, liefert es root zurück, wenn ich dann in der Suche nach * müssten alle Dateien aufgelistet werden!
        LinkedList<Datei> linkedList = dateisystem.dateiSuche("", "*");
        for (Datei datei :
                linkedList) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                datei.setDateiInhalt(Arrays.toString(cipher.doFinal(datei.getDateiInhalt().getBytes())));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
    }
}
