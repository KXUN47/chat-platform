package com.matlasystems.chat.common.interfaces;

/**
 * Generic mapper contract.
 *
 * @param <S> Source type
 * @param <T> Target type
 */
@FunctionalInterface
public interface Mapper<S, T> {

    /**
     * Maps a source object into another type.
     *
     * @param source Source object
     * @return Target object
     */
    T map(S source);

}
