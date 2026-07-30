package com.matlasystems.chat.common.constants;

/**
 * Executor and scheduled-task configuration defaults.
 */
public final class ThreadConstants {

    private ThreadConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String ACCEPT_THREAD_NAME = "chat-accept";
    public static final String WORKER_THREAD_PREFIX = "chat-worker-";
    public static final String CLEANUP_THREAD_PREFIX = "chat-cleanup-";
    public static final String HEARTBEAT_THREAD_PREFIX = "chat-heartbeat-";

    public static final int CORE_POOL_SIZE = 20;
    public static final int MAX_POOL_SIZE = 100;
    public static final int WORK_QUEUE_CAPACITY = 500;
    public static final long KEEP_ALIVE_SECONDS = 60L;
    public static final int SCHEDULED_POOL_SIZE = 2;

    public static final long SESSION_CLEANUP_INTERVAL_SECONDS = 300L;
    public static final long HEARTBEAT_INTERVAL_SECONDS = 30L;
    public static final long GRACEFUL_SHUTDOWN_TIMEOUT_SECONDS = 30L;
}
