package filius.software.smb;

import java.io.Serializable;

public class SMBMessage implements Serializable {

    String message;

    public SMBMessage(String message) {
        this.message = message;
    }
}
