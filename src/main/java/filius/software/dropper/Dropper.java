package filius.software.dropper;

import java.security.PublicKey;

import filius.Main;
import filius.software.Anwendung;
import filius.software.eternalblue.EternalBlue;
import filius.software.ransomware.Ransomware;

/**
 * @author Oliver Butterwegge 
 *  This class is part of the WannaCry Visualization
 *         and install on the System the Ransomware
 */
public class Dropper extends Anwendung {

    private EternalBlue eternalBlue;
    private PublicKey publicKey;

    public Dropper(PublicKey publicKey) {
        this.eternalBlue = new EternalBlue();
        this.publicKey = publicKey;
        starten();
    }

    @Override
    public void starten() {
        if (isRansomwareInstalled())
            scanNetwork();
        else
            installRansomware();
    }

    public PublicKey getPublicKey(){
        return publicKey;
    }

    private void installRansomware() {
        this.getSystemSoftware().installiereSoftware("Ransomware");
        Ransomware ransomware = (Ransomware) this.getSystemSoftware().holeSoftware("Ransomware");
        ransomware.setPublicKey(publicKey);
        ransomware.starten();
        scanNetwork();
    }

    private void scanNetwork() {
        String standardGateway = this.getSystemSoftware().getStandardGateway();
        String[] splittedIp = standardGateway.split(".");
        for (int index = Integer.parseInt(splittedIp[3]) + 1; index < 256; index++) {
            useEternalBlue(splittedIp[0] + "." + splittedIp[0] + "." + splittedIp[0] + "." + index);
            // Wait until try to reach another system
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace(Main.debug);
            }
        }
    }

    private void useEternalBlue(String entry) {
        this.eternalBlue.infect(entry);
    }

    private boolean isRansomwareInstalled() {
        return this.getSystemSoftware().getInstallierteAnwendungen().containsKey("Ransomware");
    }
}
