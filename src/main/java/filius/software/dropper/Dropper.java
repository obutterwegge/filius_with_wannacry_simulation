package filius.software.dropper;

import java.util.Map;
import java.util.Map.Entry;

import filius.Main;
import filius.exception.TimeOutException;
import filius.exception.VerbindungsException;
import filius.software.Anwendung;
import filius.software.eternalblue.EternalBlue;
import filius.software.transportschicht.TCPSocket;
import filius.software.vermittlungsschicht.ARP;

/**
 * @author Oliver Butterwegge This class is part of the WannaCry Visualization
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
    }

    private void scanNetwork() {
        ARP arp = this.getSystemSoftware().holeARP();
        Map<String, String> arpTable = arp.holeARPTabelle();
        for (Map.Entry<String, String> entry : arpTable.entrySet()) {
            if (entry.getKey() != this.getSystemSoftware().holeIPAdresse()) {
                useEternalBlue(entry);
            }
        }
    }

    private void useEternalBlue(Entry<String, String> entry) {
        this.eternalBlue.infect(entry.getKey());
    }

    private boolean isRansomwareInstalled() {
        return this.getSystemSoftware().getInstallierteAnwendungen().containsKey("Ransomware");
    }
}
