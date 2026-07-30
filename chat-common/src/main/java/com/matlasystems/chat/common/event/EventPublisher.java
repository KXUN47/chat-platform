package com.matlasystems.chat.common.event;
/** Contract for components that dispatch application events. */
public interface EventPublisher {
    void publish(ApplicationEvent event);
    void addListener(EventListener<ApplicationEvent> listener);
    void removeListener(EventListener<ApplicationEvent> listener);
}
