package filius.software.SMB;

import java.io.Serializable;

public class SMBMessage implements Serializable {

    String message;

    public SMBMessage(String message) {
        this.message = message;
    }
}
