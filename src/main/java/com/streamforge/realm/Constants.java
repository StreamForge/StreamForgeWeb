package com.streamforge.realm;

import java.util.UUID;

public class Constants {
    public static final String INTEGRATION_PATH = "/integration";
    public static final String AUTHENTICATION_PATH = "/authenticate";

    public static final String TWITCH_ACCESS_CODE_DTO_METHOD_NAME = "transformToTwitchAccessCodeDto";

    public static final String Q_MARK = "?";

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
