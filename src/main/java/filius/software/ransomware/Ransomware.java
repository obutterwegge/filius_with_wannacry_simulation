package filius.software.ransomware;

import filius.Main;
import filius.gui.GUIContainer;
import filius.software.Anwendung;
import filius.software.system.Datei;
import filius.software.system.Dateisystem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Base64;
import java.util.Enumeration;

public class Ransomware extends Anwendung {

    private String publicKey;

    public Ransomware(){
        super();
    }
    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public void starten(){
        super.starten();
        ausfuehren("encryptData", null);
    }
    /**
     * Encrypt all Data in the Filesystem
     */
    public void encryptData() {
        Main.debug.println("Verschlüsseln der Daten wird angefangen");
        Dateisystem dateisystem = getSystemSoftware().getDateisystem();
        for (Enumeration enumeration = dateisystem.getRoot().children(); enumeration.hasMoreElements();) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            if (node.getUserObject() instanceof Datei) {
                Datei tmpDatei = (Datei) node.getUserObject();
                if (!"PrivateKey.txt".equals(tmpDatei.getName())){
                    // TODO: 18.05.20 Implement the asymmetric cryptography
                    /*
                    try {
                        Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding", "BC");
                        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
                        cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParameterSpec);
                        tmpDatei.setDateiInhalt(Arrays.toString(cipher.doFinal(tmpDatei.getDateiInhalt().getBytes())));
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchProviderException | InvalidAlgorithmParameterException exception) {
                        exception.printStackTrace(Main.debug);
                    }*/
                    // TODO: 18.05.20 Delete after the asymmetric cryptography is implemented
                    Base64.Encoder encoder = Base64.getEncoder();
                    tmpDatei.setDateiInhalt(encoder.encodeToString(tmpDatei.getDateiInhalt().getBytes()));
                    Main.debug.println("Datei "+ tmpDatei.getName() + " ist verschlüsselt");
                }
            }
        }
        String iconPath;
        if (this.getSystemSoftware().getKnoten().holeHardwareTyp().equals("Notebook")){
            iconPath  = "gfx/hardware/laptop_encrypted.png";
        } else {
            iconPath = "gfx/hardware/server_encrypted.png";
        }
        GUIContainer.getGUIContainer().updateKnotenImage(this.getSystemSoftware().getKnoten(), iconPath);
        this.getSystemSoftware().benachrichtigeBeobacher(null);
    }

    /**
     * Decrypt all Data in the Filesystem
     */
    public void decryptData(String privateKeyString) {
        Main.debug.println("Entschlüsselung fängt an");
//        PrivateKey privateKey = (PrivateKey) args[0];
        for (Enumeration enumeration = this.getSystemSoftware().getDateisystem().getRoot().children(); enumeration.hasMoreElements();) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            if (node.getUserObject() instanceof Datei) {
                Datei tmpDatei = (Datei) node.getUserObject();
                if (!"PrivateKey.txt".equals(tmpDatei.getName())) {
                    // TODO: 18.05.20 Implement the asymmetric cryptography
                    /*try {
                        Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA256AndMGF1Padding", "BC");
                        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
                        cipher.init(Cipher.DECRYPT_MODE, publicKey, oaepParameterSpec);
                        tmpDatei.setDateiInhalt(Arrays.toString(cipher.doFinal(tmpDatei.getDateiInhalt().getBytes())));
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchProviderException | InvalidAlgorithmParameterException exception) {
                        exception.printStackTrace(Main.debug);
                    }*/
                    // TODO: 18.05.20 Delete after the asymmetric cryptography is implemented
                    Base64.Decoder decoder = Base64.getDecoder();
                    byte[] decodedContentInByte = decoder.decode(tmpDatei.getDateiInhalt().getBytes());
                    tmpDatei.setDateiInhalt(new String(decodedContentInByte));
                    Main.debug.println("Datei"+ tmpDatei.getName() + " ist entschlüsselt");
                }
            }
        }

        String iconPath;
        if (this.getSystemSoftware().getKnoten().holeHardwareTyp().equals("Notebook")){
            iconPath  = "gfx/hardware/laptop.png";
        } else {
            iconPath = "gfx/hardware/server.png";
        }
        GUIContainer.getGUIContainer().updateKnotenImage(this.getSystemSoftware().getKnoten(), iconPath);
    }

    /**
     * Method which will be called, if the decrypt-button in the GUI is pressed
     * @param privateKeyString From the Textfield the private-key for the decryption
     */
    public void decryptDataAsyncrhon(String privateKeyString) {
        Main.debug.println("Entschlüsselung aller Dateien wird ausgeführt");
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] privateKeyByte = decoder.decode(privateKeyString);
        // TODO: 18.05.20 Richtige Implementierung des PrivateKey
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        Object[] args = new Object[1];
        args[0] = privateKeyString;
        ausfuehren("decryptData", args);
    }
}
