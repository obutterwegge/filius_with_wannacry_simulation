package filius.software.SMB;

import filius.software.clientserver.ServerAnwendung;
import filius.software.transportschicht.Socket;
import filius.software.vermittlungsschicht.IpPaket;

public class SMBServer extends ServerAnwendung {

    public SMBServer(){
        super(IpPaket.TCP);
        this.port = 33099;
    }

    public void annehmenVerbindungen(){
        super.annehmenVerbindungen();
    }

    @Override
    protected void neuerMitarbeiter(Socket socket) {
        SMBServerMitarbeiter smbServerMitarbeiter = new SMBServerMitarbeiter(this, socket);
        smbServerMitarbeiter.starten();
        mitarbeiter.add(smbServerMitarbeiter);
    }
}
