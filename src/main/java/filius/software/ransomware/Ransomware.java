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
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
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
        ausfuehren("encryptData", null);
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
    public void decryptData(Object[] args) {
        PrivateKey privateKey = (PrivateKey) args[0];
        for (Enumeration enumeration = this.getSystemSoftware().getDateisystem().getRoot().children(); enumeration.hasMoreElements();) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            if (node.getUserObject() instanceof Datei) {
                Datei tmpDatei = (Datei) node.getUserObject();
                if (!"PrivateKey.txt".equals(tmpDatei.getName())) {
                    try {
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        tmpDatei.setDateiInhalt(Arrays.toString(cipher.doFinal(tmpDatei.getDateiInhalt().getBytes())));
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException exception) {
                        exception.printStackTrace(Main.debug);
                    }
                }
            }
        }
    }

    public void decryptDataAsyncrhon(String privateKeyString) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] privateKeyByte = decoder.decode(privateKeyString);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
            Object[] args = new Object[1];
            args[0] = privateKey;
            ausfuehren("decryptData", args);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
