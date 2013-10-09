package org.cidrz.webapp.dynasite.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Basic IP address utilities
 */
public class IPUtils {
    /**
     * @param b Array of 4 bytes containing the value of the input integer.
     *          b[0] contains the most significant byte
     * @return A 32 bit integer value of the IPAddress.
     */
    private static int byteArray2Int(byte[] b) {
        return byteArray2Int(b, 0);
    }

    private static int byteArray2Int(byte[] b, int pos) {
        return (b[pos] << 24) +
                ((b[pos + 1] & 0xff) << 16) +
                ((b[pos + 2] & 0xff) << 8) +
                (b[pos + 3] & 0xff);
    }

    /**
     * Converts a IP address string to an int.
     */
    public static Long parseLong(String ipaddress) throws NumberFormatException {
        InetAddress inet = null;
        try {
            inet = InetAddress.getByName(ipaddress);
        } catch (UnknownHostException ex) {
            throw new NumberFormatException("Cannot convert this IP address: " + ipaddress);
        }
        return new Long(byteArray2Int(inet.getAddress()));
    }

}
