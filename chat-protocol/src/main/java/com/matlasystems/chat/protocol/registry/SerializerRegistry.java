package com.matlasystems.chat.protocol.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.matlasystems.chat.protocol.serializer.Serializer;

public class SerializerRegistry {

    private final Map<Class<? extends Serializer<?>>, Serializer<?>> serializers =
            new ConcurrentHashMap<>();

    /**
     * Registers a serializer.
     *
     * @param type serializer type
     * @param serializer serializer instance
     * @param <T> serializer type
     */
    public <T extends Serializer<?>> void register(
            Class<T> type,
            T serializer) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Serializer type cannot be null.");
        }

        if (serializer == null) {
            throw new IllegalArgumentException(
                    "Serializer cannot be null.");
        }

        Serializer<?> existing =
                serializers.putIfAbsent(
                        type,
                        serializer);

        if (existing != null) {
            throw new DuplicateRegistrationException(
                    "Serializer already registered: "
                            + type.getSimpleName());
        }

    }

    /**
     * Finds a registered serializer.
     *
     * @param type serializer type
     * @param <T> serializer type
     * @return optional serializer
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializer<?>> Optional<T> find(
            Class<T> type) {

        return Optional.ofNullable(
                (T) serializers.get(type));

    }

    /**
     * Checks whether a serializer is registered.
     *
     * @param type serializer type
     * @return true if registered
     */
    public boolean exists(
            Class<? extends Serializer<?>> type) {

        return serializers.containsKey(type);

    }

    /**
     * Removes a serializer.
     *
     * @param type serializer type
     */
    public void remove(
            Class<? extends Serializer<?>> type) {

        serializers.remove(type);

    }

    /**
     * Returns all registered serializers.
     *
     * @return unmodifiable collection of serializers
     */
    public Collection<Serializer<?>> getAll() {

        return Collections.unmodifiableCollection(
                serializers.values());

    }

    /**
     * Returns the number of registered serializers.
     *
     * @return registry size
     */
    public int size() {

        return serializers.size();

    }

    /**
     * Removes all registered serializers.
     */
    public void clear() {

        serializers.clear();

    }

}
