package filius.software.dropper;

import java.security.PublicKey;
import java.util.HashMap;

import filius.Main;
import filius.software.Anwendung;
import filius.software.exploit.Exploit;
import filius.software.ransomware.Ransomware;
import filius.software.system.InternetKnotenBetriebssystem;

/**
 * @author Oliver Butterwegge 
 *  This class is part of the WannaCry Visualization
 *         and install on the System the Ransomware
 */
public class Dropper extends Anwendung {

    public final Exploit exploit;
    private final String publicKey;

    public Dropper(String publicKey, InternetKnotenBetriebssystem internetKnotenBetriebssystem) {
        super();
        this.exploit = new Exploit(internetKnotenBetriebssystem);
        this.publicKey = publicKey;
        this.setSystemSoftware(internetKnotenBetriebssystem);
    }

    @Override
    public void starten() {
        super.starten();
        System.out.println("Dropper gestartet");
        checkIfRansomwareIsInstalled();
    }

    private void checkIfRansomwareIsInstalled() {
        System.out.println("Prüfe ob Ransomware installiert ist");
        if (isRansomwareInstalled()){
            System.out.println("Ransomware ist installiert, starte mit Scan des Netzwerkes");
            ausfuehren("scanNetwork", null);
        }
        else{
            System.out.println("Ransomware ist nicht installiert");
            installRansomware();
        }
    }

    public String getPublicKey(){
        return publicKey;
    }

    private void installRansomware() {
        System.out.println("Ransomware wird installiert");
        this.getSystemSoftware().installiereSoftware("filius.software.ransomware.Ransomware");
        Ransomware ransomware = (Ransomware) this.getSystemSoftware().holeSoftware("filius.software.ransomware.Ransomware");
        System.out.println("Ransomware wurde erfolgreich installiert");
        ransomware.setPublicKey(publicKey);
        ransomware.starten();
        System.out.println("Ransomware wurde gestartet");
        checkIfRansomwareIsInstalled();
    }

    public void scanNetwork() {
        Main.debug.println("Scan des Netzwerk initialisiert");
        String standardGateway = this.getSystemSoftware().holeIPAdresse();
        String[] splittedIp = standardGateway.split("\\.");
        this.getSystemSoftware().benachrichtigeBeobacher("Test");
        for ( int index = 1; index < 256; index++) {
            String ipAddress = splittedIp[0] + "." + splittedIp[1] + "." + splittedIp[2] + "." + index;
            System.out.println("Es wird versucht das System mit der IP "+ipAddress+" zu infizieren");
            useEternalBlue(ipAddress, publicKey);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace(Main.debug);
            }
        }
    }

    private void useEternalBlue(String entry, String publicKey) {
        this.exploit.infect(entry, publicKey);
    }

    private boolean isRansomwareInstalled() {
        HashMap<String, Anwendung> installedApplications = this.getSystemSoftware().getInstallierteAnwendungen();
        for (Anwendung application :
                installedApplications.values()) {
            if (application instanceof Ransomware)
                return true;
        }
        return false;
    }
}
