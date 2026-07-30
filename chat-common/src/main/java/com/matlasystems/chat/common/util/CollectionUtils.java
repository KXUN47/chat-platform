package com.matlasystems.chat.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Utility methods for working with collections.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns true if the collection is null or empty.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Returns true if the collection contains data.
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Returns an empty immutable list if the supplied list is null.
     */
    public static <T> List<T> safeList(List<T> list) {
        return list == null
                ? Collections.emptyList()
                : list;
    }

    /**
     * Returns an empty immutable set if the supplied set is null.
     */
    public static <T> Set<T> safeSet(Set<T> set) {
        return set == null
                ? Collections.emptySet()
                : set;
    }

    /**
     * Returns the first element or null.
     */
    public static <T> T first(List<T> list) {

        if (isEmpty(list)) {
            return null;
        }

        return list.get(0);
    }

    /**
     * Returns the last element or null.
     */
    public static <T> T last(List<T> list) {

        if (isEmpty(list)) {
            return null;
        }

        return list.get(list.size() - 1);
    }

    /**
     * Returns true if the collection contains the value.
     */
    public static <T> boolean contains(
            Collection<T> collection,
            T value) {

        return collection != null &&
                collection.contains(value);
    }

    /**
     * Returns an immutable copy.
     */
    public static <T> List<T> immutableList(
            Collection<T> collection) {

        if (collection == null) {
            return List.of();
        }

        return List.copyOf(collection);
    }

    /**
     * Returns an immutable copy.
     */
    public static <T> Set<T> immutableSet(
            Collection<T> collection) {

        if (collection == null) {
            return Set.of();
        }

        return Set.copyOf(collection);
    }

    /**
     * Creates a mutable ArrayList.
     */
    public static <T> List<T> mutableList(
            Collection<T> collection) {

        if (collection == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(collection);
    }

    /**
     * Creates a mutable LinkedHashSet.
     */
    public static <T> Set<T> mutableSet(
            Collection<T> collection) {

        if (collection == null) {
            return new LinkedHashSet<>();
        }

        return new LinkedHashSet<>(collection);
    }

    /**
     * Null-safe size.
     */
    public static int size(Collection<?> collection) {

        if (collection == null) {
            return 0;
        }

        return collection.size();
    }

    /**
     * Returns true if both collections contain the same elements.
     */
    public static boolean equalsIgnoreOrder(
            Collection<?> first,
            Collection<?> second) {

        if (first == second) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        return first.size() == second.size()
                && first.containsAll(second)
                && second.containsAll(first);
    }

    /**
     * Removes null values.
     */
    public static <T> List<T> removeNulls(
            Collection<T> collection) {

        if (collection == null) {
            return List.of();
        }

        return collection.stream()
                .filter(Objects::nonNull)
                .toList();
    }

}
