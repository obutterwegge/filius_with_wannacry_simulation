package filius.software.wannacry;

import filius.software.Anwendung;
import filius.software.dropper.Dropper;

/**
 * This is an abstract WannaCry for Filius
 * 
 * @author Oliver Butterwegge
 */
public class WannaCry extends Anwendung{

    @Override
    public void starten() {
        /*
        When installed, WannaCry just inizialized the Dropper
        */
        Dropper dropper = new Dropper();
        dropper.starten();
    }
}