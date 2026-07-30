package com.matlasystems.chat.common.interfaces;

/**
 * Listener interface for receiving application events.
 *
 * @param <T> event type
 */
@FunctionalInterface
public interface EventListener<T> {

    /**
     * Invoked when an event is published.
     *
     * @param event published event
     */
    void onEvent(T event);

}
