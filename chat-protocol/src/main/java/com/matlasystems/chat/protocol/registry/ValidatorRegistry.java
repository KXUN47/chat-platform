package com.matlasystems.chat.protocol.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.matlasystems.chat.protocol.validator.Validator;

/**
 * Registry for protocol validators.
 */
public class ValidatorRegistry {

    private final Map<Class<? extends Validator<?>>, Validator<?>> validators =
            new ConcurrentHashMap<>();

    /**
     * Registers a validator.
     *
     * @param type validator type
     * @param validator validator instance
     * @param <T> validator type
     */
    public <T extends Validator<?>> void register(
            Class<T> type,
            T validator) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Validator type cannot be null.");
        }

        if (validator == null) {
            throw new IllegalArgumentException(
                    "Validator cannot be null.");
        }

        Validator<?> existing =
                validators.putIfAbsent(
                        type,
                        validator);

        if (existing != null) {

            throw new DuplicateRegistrationException(
                    "Validator already registered: "
                            + type.getSimpleName());

        }

    }

    /**
     * Finds a registered validator.
     *
     * @param type validator type
     * @param <T> validator type
     * @return optional validator
     */
    @SuppressWarnings("unchecked")
    public <T extends Validator<?>> Optional<T> find(
            Class<T> type) {

        return Optional.ofNullable(
                (T) validators.get(type));

    }

    /**
     * Checks whether a validator is registered.
     *
     * @param type validator type
     * @return true if registered
     */
    public boolean exists(
            Class<? extends Validator<?>> type) {

        return validators.containsKey(type);

    }

    /**
     * Removes a validator.
     *
     * @param type validator type
     */
    public void remove(
            Class<? extends Validator<?>> type) {

        validators.remove(type);

    }

    /**
     * Returns all registered validators.
     *
     * @return unmodifiable collection of validators
     */
    public Collection<Validator<?>> getAll() {

        return Collections.unmodifiableCollection(
                validators.values());

    }

    /**
     * Returns the number of registered validators.
     *
     * @return registry size
     */
    public int size() {

        return validators.size();

    }

    /**
     * Removes all registered validators.
     */
    public void clear() {

        validators.clear();

    }

}
