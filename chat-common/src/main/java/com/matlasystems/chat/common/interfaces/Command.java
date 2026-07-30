package com.matlasystems.chat.common.interfaces;

/**
 * Represents an executable command within the chat platform.
 *
 * Implementations encapsulate a single unit of work,
 * such as LOGIN, LOGOUT or MESSAGE.
 *
 * @param <R> Request type
 * @param <S> Response type
 */
@FunctionalInterface
public interface Command<R, S> {

    /**
     * Executes the command.
     *
     * @param request Request object
     * @return Response object
     */
    S execute(R request);

}
