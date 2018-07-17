package com.streamforge.realm.twitch.oauth.camel;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URISyntaxException;

import static com.streamforge.realm.Constants.*;

@Component
public class TwitchHttpQueryBuilder {
    @Value("${streamforge.twitch.auth-uri}")
    private String authURI;

    @Value("${streamforge.twitch.redirect-uri}")
    private String redirectURI;

    @Value("${streamforge.twitch.client-id}")
    private String clientId;

    @Value("${streamforge.twitch.client-secret}")
    private String clientSecret;

    @Value("${streamforge.twitch.response}")
    private String response;

    public String createAuthURI() throws URISyntaxException {
        return new URIBuilder(authURI)
                .addParameter(CLIENT_ID_PROPERTY, clientId)
                .addParameter(REDIRECT_URI_PROPERTY, redirectURI)
                .addParameter(RESPONSE_TYPE_PROPERTY, response)
                .addParameter(SCOPE_PROPERTY, SCOPE_VALUE)
                .addParameter(STATE_PROPERTY, STATE)
                .build().toString();
    }

    public MultiValueMap<String, String> createTokenRequestURI() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(CLIENT_ID_PROPERTY, clientId);
        params.add(CLIENT_SECRET_PROPERTY, clientSecret);
        params.add(REDIRECT_URI_PROPERTY, redirectURI);
        params.add(GRANT_TYPE_PROPERTY, GRANT_TYPE_VALUE);
        return params;
    }
}
