package com.matlasystems.chat.common.constants;

/**
 * HTTP API paths, headers and media types.
 */
public final class ApiConstants {

    private ApiConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String API_VERSION = "v1";
    public static final String API_BASE_PATH = "/api/" + API_VERSION;
    public static final String HEALTH_PATH = "/health";
    public static final String AUTH_PATH = API_BASE_PATH + "/auth";
    public static final String USERS_PATH = API_BASE_PATH + "/users";
    public static final String MESSAGES_PATH = API_BASE_PATH + "/messages";
    public static final String FILES_PATH = API_BASE_PATH + "/files";

    public static final String LOGIN_PATH = AUTH_PATH + "/login";
    public static final String LOGOUT_PATH = AUTH_PATH + "/logout";
    public static final String REGISTER_PATH = AUTH_PATH + "/register";

    public static final String APPLICATION_JSON = "application/json";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String ACCEPT_HEADER = "Accept";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String REQUEST_ID_HEADER = "X-Request-Id";

    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_NO_CONTENT = 204;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_CONFLICT = 409;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
}
