package filius.software.ransomware;

import filius.Main;
import filius.software.Anwendung;
import filius.software.system.Datei;
import filius.software.system.Dateisystem;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.tree.DefaultMutableTreeNode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;

public class Ransomware extends Anwendung {

    private PublicKey publicKey;

    public Ransomware(){
        super();
    }
    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void starten() {
        encryptData();
    }

    /**
     * Encrypt all Data in the Filesystem
     */
    public void encryptData() {
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        for (Enumeration enumeration = dateisystem.getRoot().children(); enumeration.hasMoreElements();) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            if (node.getUserObject() instanceof Datei) {
                Datei tmpDatei = (Datei) node.getUserObject();
                if (!"PrivateKey.txt".equals(tmpDatei.getName())){
                    try {
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                        tmpDatei.setDateiInhalt(Arrays.toString(cipher.doFinal(tmpDatei.getDateiInhalt().getBytes())));
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException exception) {
                        exception.printStackTrace(Main.debug);
                    }
                }
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
            if (!"PrivateKey.txt".equals(datei.getName())) {
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
}
