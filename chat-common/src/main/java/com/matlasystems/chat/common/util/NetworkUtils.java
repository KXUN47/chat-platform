package com.matlasystems.chat.common.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
/**
 * Network helper utilities.
 */
public final class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns local host.
     */
    public static InetAddress localhost()
            throws UnknownHostException {

        return InetAddress.getLocalHost();
    }

    /**
     * Returns hostname.
     */
    public static String hostname()
            throws UnknownHostException {

        return localhost().getHostName();
    }

    /**
     * Returns IP address.
     */
    public static String localIp()
            throws UnknownHostException {

        return localhost().getHostAddress();
    }

    /**
     * Resolves a hostname.
     */
    public static InetAddress resolve(String host)
            throws UnknownHostException {

        Objects.requireNonNull(host);

        return InetAddress.getByName(host);
    }

    /**
     * Validates a TCP port.
     */
    public static boolean isValidPort(int port) {

        return port >= 1 && port <= 65535;
    }

    /**
     * Checks whether an IP address is valid.
     */
    public static boolean isValidIp(String ip) {

        if (ip == null || ip.isBlank()) {
            return false;
        }

        try {

            InetAddress.getByName(ip);

            return true;

        } catch (UnknownHostException ex) {

            return false;

        }
    }

    /**
     * Returns whether a host is reachable.
     */
    public static boolean isReachable(
            String host,
            int timeoutMillis) {

        Objects.requireNonNull(host, "host must not be null");

        try {

            InetAddress address =
                    InetAddress.getByName(host);

            return address.isReachable(timeoutMillis);

        } catch (IOException ex) {

            return false;

        }
    }

}
