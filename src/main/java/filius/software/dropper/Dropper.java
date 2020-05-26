package filius.software.dropper;

import filius.Main;
import filius.software.Anwendung;
import filius.software.exploit.Exploit;
import filius.software.ransomware.Ransomware;
import filius.software.resourceloader.ResourceLoader;
import filius.software.system.InternetKnotenBetriebssystem;

import java.util.HashMap;

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
        Main.debug.println("Dropper gestartet");
        checkIfRansomwareIsInstalled();
    }

    private void checkIfRansomwareIsInstalled() {
        Main.debug.println(this.getSystemSoftware().holeIPAdresse()+": Prüfe ob Ransomware installiert ist");
        if (isRansomwareInstalled()){
            Main.debug.println("Ransomware ist installiert, starte mit Scan des Netzwerkes");
            ausfuehren("scanNetwork", null);
        }
        else{
            Main.debug.println("Ransomware ist nicht installiert");
            installResourceLoader();
        }
    }

    private void installResourceLoader() {
        Main.debug.println(this.getSystemSoftware().holeIPAdresse()+": ResourceLoader wird installiert");
        ResourceLoader resourceLoader = new ResourceLoader(this.getSystemSoftware());
        resourceLoader.installRansomware(publicKey);
        checkIfRansomwareIsInstalled();
    }

    /*
     * Must be public because otherwise it couldn't be run asynchron
     */
    public void scanNetwork() {
        Main.debug.println("Scan des Netzwerk initialisiert");
        String standardGateway = this.getSystemSoftware().holeIPAdresse();
        String[] splittedIp = standardGateway.split("\\.");
        for ( int index = 1; index < 256; index++) {
            int random = (int) Math.floor(Math.random() * 25);
            String ipAddress = splittedIp[0] + "." + splittedIp[1] + "." + splittedIp[2] + "." + random;
            if (!ipAddress.equals(this.getSystemSoftware().holeIPAdresse())){
                Main.debug.println("Es wird versucht das System mit der IP "+ipAddress+" zu infizieren");
                useEternalBlue(ipAddress, publicKey);
            }
            try {
                sleep(5000);
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
