package filius.software.dropper;

import filius.Main;
import filius.software.Anwendung;
import filius.software.eternalblue.EternalBlue;

/**
 * @author Oliver Butterwegge 
 *  This class is part of the WannaCry Visualization
 *         and install on the System the Ransomware
 */
public class Dropper extends Anwendung {

    private EternalBlue eternalBlue;

    public Dropper() {
        this.eternalBlue = new EternalBlue();
        starten();
    }

    @Override
    public void starten() {
        if (isRansomwareInstalled())
            scanNetwork();
        else
            installRansomware();
    }

    private void installRansomware() {
        this.getSystemSoftware().installiereSoftware("Ransomware");
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
