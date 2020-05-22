package filius.software.SMB;

import filius.software.Anwendung;

public class SMBServerAnwendung extends Anwendung {

    private SMBServer smbServer;
    private boolean aktiv = false;

    public void starten(){
        super.starten();
        smbServer = new SMBServer();
        smbServer.setSystemSoftware(this.getSystemSoftware());
        smbServer.setAktiv(true);
        smbServer.starten();
    }

    @Override
    public void beenden() {
        super.beenden();
        if (smbServer != null)
            smbServer.beenden();
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
        if (smbServer != null)
            smbServer.setAktiv(aktiv);
    }
}
