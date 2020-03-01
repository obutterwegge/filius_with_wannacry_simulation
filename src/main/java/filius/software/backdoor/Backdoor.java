package filius.software.backdoor;

import filius.Main;
import filius.exception.VerbindungsException;
import filius.rahmenprogramm.Information;
import filius.software.clientserver.ClientAnwendung;
import filius.software.dns.Resolver;
import filius.software.transportschicht.UDPSocket;
import filius.software.vermittlungsschicht.IP;
import filius.software.vermittlungsschicht.Route;
import filius.software.vermittlungsschicht.RouteNotFoundException;
import filius.software.vermittlungsschicht.VermittlungsProtokoll;

public class Backdoor extends ClientAnwendung {

    public void isVictimAvailable() {

        String destIp = resolveHostname();
        //send several ICMP echo requests
        long timeStart;
        long timeDiff;
        int num;
        int loopNumber = Information.isPosixCommandLineToolBehaviour() ? 10 : 4;
        for (num = 0; num < loopNumber; num++) {
            try {
                timeStart = System.currentTimeMillis();
                int resTTL = getSystemSoftware().holeICMP().startSinglePing(destIp, num + 1);
                timeDiff = 1000 - (System.currentTimeMillis() - timeStart);
                if (resTTL >= 0) {
                    Main.debug.println("Target could be reached, Dropper is initialized");
                    installDropper(destIp);
                }
                if (timeDiff > 0) {
                    try {
                        Thread.sleep(timeDiff);
                    } catch (InterruptedException e) {
                        e.printStackTrace(Main.debug);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(filius.Main.debug);
            }
        }
    }

    private String resolveHostname() {
        // first: resolve host name
        Resolver res = getSystemSoftware().holeDNSClient();
        if (res == null) {
            Main.debug.println("ERROR (" + this.hashCode() + "): Terminal 'host': Resolver is null!");
        }
        String destIp = null;
        try {
            String victimIPAdress = "192.168.0.1";
            destIp = IP.ipCheck(victimIPAdress);
            if (destIp == null) {
                destIp = res.holeIPAdresse(victimIPAdress);
            }
            if (destIp == null) {
                Main.debug.println("not a valid IP for this system");
                destIp = "";
            }
        } catch (Exception e){
            e.printStackTrace(Main.debug);
        }
        try {
            Route route = getSystemSoftware().determineRoute(destIp);
            if (VermittlungsProtokoll.isBroadcast(destIp, route.getInterfaceIpAddress(), route.getNetMask())) {
                Main.debug.println("Broadcast is not valid");
            }
        } catch (RouteNotFoundException e1) {
            Main.debug.println("Target not reachable");
        }
        return destIp;
    }

    private void installDropper(String destIp) {
        try {
            UDPSocket udpSocket = new UDPSocket(getSystemSoftware(), destIp, 30038);
            udpSocket.senden("install");
        } catch (VerbindungsException e) {
            e.printStackTrace(Main.debug);
        }
    }
}
