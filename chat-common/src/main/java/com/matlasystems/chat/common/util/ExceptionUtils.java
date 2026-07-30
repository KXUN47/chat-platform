package com.matlasystems.chat.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utility methods for working with exceptions.
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the root cause.
     */
    public static Throwable rootCause(Throwable throwable) {

        if (throwable == null) {
            return null;
        }

        Throwable result = throwable;

        while (result.getCause() != null) {
            result = result.getCause();
        }

        return result;
    }

    /**
     * Returns the root cause message.
     */
    public static String rootCauseMessage(
            Throwable throwable) {

        Throwable root = rootCause(throwable);

        if (root == null) {
            return "";
        }

        return root.getMessage();
    }

    /**
     * Returns the full stack trace.
     */
    public static String stackTrace(
            Throwable throwable) {

        if (throwable == null) {
            return "";
        }

        StringWriter writer = new StringWriter();

        PrintWriter printWriter =
                new PrintWriter(writer);

        throwable.printStackTrace(printWriter);

        return writer.toString();
    }

    /**
     * Returns the exception message.
     */
    public static String message(
            Throwable throwable) {

        if (throwable == null) {
            return "";
        }

        return throwable.getMessage();
    }

    /**
     * Returns the exception type.
     */
    public static String type(
            Throwable throwable) {

        if (throwable == null) {
            return "";
        }

        return throwable.getClass()
                .getSimpleName();
    }

    /**
     * Returns true if the throwable has a cause.
     */
    public static boolean hasCause(
            Throwable throwable) {

        return throwable != null &&
                throwable.getCause() != null;
    }

    /**
     * Returns a concise description of an exception.
     */
    public static String summary(
            Throwable throwable) {

        if (throwable == null) {
            return "";

        }

        return type(throwable)
                + ": "
                + message(throwable);
    }

}
