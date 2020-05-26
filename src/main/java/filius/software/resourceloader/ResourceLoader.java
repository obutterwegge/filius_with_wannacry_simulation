package filius.software.resourceloader;

import filius.Main;
import filius.software.Anwendung;
import filius.software.ransomware.Ransomware;
import filius.software.system.InternetKnotenBetriebssystem;

public class ResourceLoader extends Anwendung {

    public ResourceLoader(InternetKnotenBetriebssystem systemSoftware){
        super();
        this.setSystemSoftware(systemSoftware);
    }

    public void installRansomware(String publicKey){
        this.getSystemSoftware().installiereSoftware("filius.software.ransomware.Ransomware");
        Ransomware ransomware = (Ransomware) this.getSystemSoftware().holeSoftware("filius.software.ransomware.Ransomware");
        Main.debug.println("Ransomware wurde erfolgreich installiert");
        ransomware.setPublicKey(publicKey);
        ransomware.starten();
        Main.debug.println("Ransomware wurde gestartet");
    }
}
