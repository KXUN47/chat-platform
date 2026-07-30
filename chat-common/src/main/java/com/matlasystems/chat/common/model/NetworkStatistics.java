package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * Maintains aggregated network statistics for a client or server connection.
 * <p>
 * This class tracks:
 * <ul>
 *     <li>Total bytes sent</li>
 *     <li>Total bytes received</li>
 *     <li>Total packets sent</li>
 *     <li>Total packets received</li>
 *     <li>Timestamp of the most recent network activity</li>
 * </ul>
 * <p>
 * All operations are synchronized to ensure thread safety when updated by
 * multiple networking threads.
 */
public class NetworkStatistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long bytesSent;

    private long bytesReceived;

    private long packetsSent;

    private long packetsReceived;

    private Instant lastUpdatedAt;

    /**
     * Creates an empty network statistics instance.
     */
    public NetworkStatistics() {
        this.lastUpdatedAt = Instant.now();
    }

    /**
     * Records outgoing network traffic.
     *
     * @param bytes number of bytes sent
     */
    public synchronized void recordSent(long bytes) {

        bytesSent += Math.clamp(
                bytes,
                0L,
                Long.MAX_VALUE);

        packetsSent++;
        lastUpdatedAt = Instant.now();
    }

    /**
     * Records incoming network traffic.
     *
     * @param bytes number of bytes received
     */
    public synchronized void recordReceived(long bytes) {

        bytesReceived += Math.clamp(
                bytes,
                0L,
                Long.MAX_VALUE);

        packetsReceived++;
        lastUpdatedAt = Instant.now();
    }

    /**
     * Returns the total bytes sent.
     *
     * @return bytes sent
     */
    public synchronized long getBytesSent() {
        return bytesSent;
    }

    /**
     * Sets the total bytes sent.
     *
     * @param bytesSent bytes sent
     */
    public synchronized void setBytesSent(long bytesSent) {
        this.bytesSent = Math.clamp(
                bytesSent,
                0L,
                Long.MAX_VALUE);
    }

    /**
     * Returns the total bytes received.
     *
     * @return bytes received
     */
    public synchronized long getBytesReceived() {
        return bytesReceived;
    }

    /**
     * Sets the total bytes received.
     *
     * @param bytesReceived bytes received
     */
    public synchronized void setBytesReceived(long bytesReceived) {
        this.bytesReceived = Math.clamp(
                bytesReceived,
                0L,
                Long.MAX_VALUE);
    }

    /**
     * Returns the number of packets sent.
     *
     * @return packets sent
     */
    public synchronized long getPacketsSent() {
        return packetsSent;
    }

    /**
     * Sets the number of packets sent.
     *
     * @param packetsSent packets sent
     */
    public synchronized void setPacketsSent(long packetsSent) {
        this.packetsSent = Math.clamp(
                packetsSent,
                0L,
                Long.MAX_VALUE);
    }

    /**
     * Returns the number of packets received.
     *
     * @return packets received
     */
    public synchronized long getPacketsReceived() {
        return packetsReceived;
    }

    /**
     * Sets the number of packets received.
     *
     * @param packetsReceived packets received
     */
    public synchronized void setPacketsReceived(long packetsReceived) {
        this.packetsReceived = Math.clamp(
                packetsReceived,
                0L,
                Long.MAX_VALUE);
    }

    /**
     * Returns the timestamp of the most recent activity.
     *
     * @return last updated timestamp
     */
    public synchronized Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    /**
     * Sets the timestamp of the most recent activity.
     *
     * @param lastUpdatedAt last updated timestamp
     */
    public synchronized void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    /**
     * Returns the total number of bytes transferred.
     *
     * @return total bytes transferred
     */
    public synchronized long getTotalBytes() {
        return bytesSent + bytesReceived;
    }

    /**
     * Returns the total number of packets transferred.
     *
     * @return total packets transferred
     */
    public synchronized long getTotalPackets() {
        return packetsSent + packetsReceived;
    }

    /**
     * Resets all network statistics.
     */
    public synchronized void reset() {

        bytesSent = 0L;
        bytesReceived = 0L;
        packetsSent = 0L;
        packetsReceived = 0L;
        lastUpdatedAt = Instant.now();
    }

}
