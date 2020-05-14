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

    private final Exploit exploit;
    private final PublicKey publicKey;
    private Ransomware ransomware;

    public Dropper(PublicKey publicKey, InternetKnotenBetriebssystem internetKnotenBetriebssystem) {
        super();
        this.exploit = new Exploit(internetKnotenBetriebssystem);
        this.publicKey = publicKey;
        this.setSystemSoftware(internetKnotenBetriebssystem);
    }

    @Override
    public void starten() {
        checkIfRansomwareIsInstalled();
    }

    private void checkIfRansomwareIsInstalled() {
        if (isRansomwareInstalled()){
            ausfuehren("scanNetwork", null);
//            scanNetwork();
        }
        else{
            installRansomware();
        }
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    private void installRansomware() {
        this.getSystemSoftware().installiereSoftware("filius.software.ransomware.Ransomware");
        ransomware = (Ransomware) this.getSystemSoftware().holeSoftware("filius.software.ransomware.Ransomware");
        ransomware.setPublicKey(publicKey);
        ransomware.starten();
        checkIfRansomwareIsInstalled();
    }

    private void scanNetwork() {
        String standardGateway = this.getSystemSoftware().holeIPAdresse();
        String[] splittedIp = standardGateway.split("\\.");
        for ( int index = 1; index < 256; index++) {
            useEternalBlue(splittedIp[0] + "." + splittedIp[1] + "." + splittedIp[2] + "." + index, publicKey);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace(Main.debug);
            }
        }
//        useEternalBlue("192.168.0.11", publicKey);
    }

    private void useEternalBlue(String entry, PublicKey publicKey) {
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
