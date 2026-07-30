package com.matlasystems.chat.common.interfaces;

/**
 * Generic validation contract.
 *
 * @param <T> Object being validated
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates an object.
     *
     * Implementations should throw a ValidationException
     * when validation fails.
     *
     * @param object Object to validate
     */
    void validate(T object);

}
