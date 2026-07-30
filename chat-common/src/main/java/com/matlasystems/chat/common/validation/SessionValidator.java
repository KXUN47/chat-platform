package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.entity.Session;
import com.matlasystems.chat.common.util.ValidationUtils;

/**
 * Validates the mandatory fields of an authenticated session.
 */
public final class SessionValidator
        implements Validator<Session> {

    private static final String SESSION_REQUIRED =
            "Session is required";

    private static final String SESSION_ID_REQUIRED =
            "Session ID is required";

    private static final String USER_REQUIRED =
            "Session user is required";

    private static final String LOGIN_TIME_REQUIRED =
            "Login time is required";

    private static final String LAST_ACTIVITY_REQUIRED =
            "Last activity time is required";

    private static final String STATUS_REQUIRED =
            "Session status is required";

    private static final String PORT_RANGE =
            "Port must be between 1 and 65535";

    @Override
    public ValidationResult validate(Session session) {

        ValidationResult result = ValidationResult.valid();

        if (session == null) {
            return result.addError(
                    "session",
                    ValidationCodes.REQUIRED,
                    SESSION_REQUIRED);
        }

        if (session.getSessionId() == null) {
            result.addError(
                    "sessionId",
                    ValidationCodes.REQUIRED,
                    SESSION_ID_REQUIRED);
        }

        if (session.getUserId() == null) {
            result.addError(
                    "userId",
                    ValidationCodes.REQUIRED,
                    USER_REQUIRED);
        }

        if (session.getLoginTime() == null) {
            result.addError(
                    "loginTime",
                    ValidationCodes.REQUIRED,
                    LOGIN_TIME_REQUIRED);
        }

        if (session.getLastActivity() == null) {
            result.addError(
                    "lastActivity",
                    ValidationCodes.REQUIRED,
                    LAST_ACTIVITY_REQUIRED);
        }

        if (session.getStatus() == null) {
            result.addError(
                    "status",
                    ValidationCodes.REQUIRED,
                    STATUS_REQUIRED);
        }

        if (session.getPort() != null
                && !ValidationUtils.isValidPort(
                        session.getPort())) {

            result.addError(
                    "port",
                    ValidationCodes.RANGE,
                    PORT_RANGE);
        }

        return result;
    }

}
