package com.matlasystems.chat.common.interfaces;

/**
 * Represents a resource that can be safely closed.
 *
 * Implementations should release all underlying resources.
 */
public interface CloseableResource extends AutoCloseable {

    /**
     * Releases all resources held by this object.
     *
     * Implementations should be idempotent, meaning
     * calling close() multiple times should not cause errors.
     */
    @Override
    void close();

}
