package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a serializable snapshot of a Java thread.
 * <p>
 * This class captures the essential properties of a thread at a
 * specific point in time for diagnostics, monitoring, and debugging.
 */
public class ThreadInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long threadId;

    private String name;

    private String state;

    private boolean daemon;

    private int priority;

    /**
     * Creates an empty thread information instance.
     * <p>
     * Required for serialization frameworks.
     */
    public ThreadInfo() {
        // Required for serialization frameworks.
    }

    /**
     * Creates a thread information snapshot from the specified thread.
     *
     * @param thread thread to inspect
     * @return populated thread information
     * @throws NullPointerException if {@code thread} is {@code null}
     */
    public static ThreadInfo from(Thread thread) {

        ThreadInfo threadInfo = new ThreadInfo();

        threadInfo.threadId = thread.threadId();
        threadInfo.name = thread.getName();
        threadInfo.state = thread.getState().name();
        threadInfo.daemon = thread.isDaemon();
        threadInfo.priority = thread.getPriority();

        return threadInfo;
    }

    /**
     * Returns the thread identifier.
     *
     * @return thread identifier
     */
    public long getThreadId() {
        return threadId;
    }

    /**
     * Sets the thread identifier.
     *
     * @param threadId thread identifier
     */
    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    /**
     * Returns the thread name.
     *
     * @return thread name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the thread name.
     *
     * @param name thread name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the thread state.
     *
     * @return thread state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the thread state.
     *
     * @param state thread state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Indicates whether the thread is a daemon thread.
     *
     * @return {@code true} if the thread is a daemon thread;
     *         {@code false} otherwise
     */
    public boolean isDaemon() {
        return daemon;
    }

    /**
     * Sets whether the thread is a daemon thread.
     *
     * @param daemon daemon flag
     */
    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    /**
     * Returns the thread priority.
     *
     * @return thread priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the thread priority.
     *
     * @param priority thread priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
