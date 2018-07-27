package com.streamforge.realm;

import java.util.UUID;

public class Constants {
    public static final String SCRIPT_TYPE_JS = "text/javascript";
    public static final String INNER_SCRIPT_REDIRECT = "window.location.replace('%s');";
    public static final String INNER_SCRIPT_STORAGE = "localStorage.setItem('token', '%s');";
    public static final String INNER_SCRIPT_STATE_ERROR = "localStorage.setItem('stateError', 'true');";
    public static final String INNER_SCRIPT_TOKEN_ERROR = "localStorage.setItem('tokenError', 'true');";

    public static final String BASE_URL = "http://localhost:7001/";

    public static final String CLIENT_ID_PROPERTY = "client_id";
    public static final String CLIENT_SECRET_PROPERTY = "client_secret";
    public static final String REDIRECT_URI_PROPERTY = "redirect_uri";
    public static final String RESPONSE_TYPE_PROPERTY = "response_type";
    public static final String GRANT_TYPE_PROPERTY = "grant_type";
    public static final String SCOPE_PROPERTY = "scope";
    public static final String STATE_PROPERTY = "state";
    public static final String CODE_PROPERTY = "code";
    public static final String GRANT_TYPE_VALUE = "authorization_code";
    public static final String SCOPE_VALUE = "user_read";

    public static final String STATE = UUID.randomUUID().toString();
}
