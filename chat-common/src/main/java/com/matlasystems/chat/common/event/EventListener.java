package com.matlasystems.chat.common.event;
/** Receives an application event. */
@FunctionalInterface
public interface EventListener<E extends ApplicationEvent> { void onEvent(E event); }
