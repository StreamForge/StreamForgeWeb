package com.streamforge.realm.twitch.oauth.service.impl;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Script;
import com.hp.gagawa.java.elements.Text;
import com.streamforge.http.request.SimpleRequestWrapper;
import com.streamforge.realm.twitch.oauth.builder.TwitchHttpQueryBuilder;
import com.streamforge.realm.twitch.oauth.service.TwitchAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

import static com.streamforge.realm.Constants.*;

@Service
public class TwitchAuthServiceImpl implements TwitchAuthService {

    @Value("${streamforge.twitch.token-uri}")
    private String tokenURI;

    @Value("${streamforge.services.savepath}")
    private String serviceSavePath;

    private static final String TOKEN_PATTERN = "^Custom [a-zA-Z0-9]{10,}$";

    private final TwitchHttpQueryBuilder twitchHttpQueryBuilder;

    @Autowired
    public TwitchAuthServiceImpl(TwitchHttpQueryBuilder twitchHttpQueryBuilder) {
        this.twitchHttpQueryBuilder = twitchHttpQueryBuilder;
    }

    @Override
    public String processCallback(String code, String status) {
        String token = requestForToken(twitchHttpQueryBuilder.createTokenRequestURI(code));
        String restResponse = token != null ? saveTokenRequest(token) : null;
        return generateProcessingHTML(restResponse, status);
    }

    private String generateProcessingHTML(String token, String status) {
        Body body = new Body();
        Script script = new Script(SCRIPT_TYPE_JS);
        StringBuilder scriptBuilder = new StringBuilder();
        if (token == null || !token.matches(TOKEN_PATTERN)) {
            scriptBuilder.append(INNER_SCRIPT_TOKEN_ERROR);
        } else if ((status != null && !status.equals(STATE))) {
            scriptBuilder.append(INNER_SCRIPT_STATE_ERROR);
        } else {
            scriptBuilder.append(String.format(INNER_SCRIPT_STORAGE, token));
        }
        scriptBuilder.append(String.format(INNER_SCRIPT_REDIRECT, BASE_URL));
        script.appendChild(new Text(scriptBuilder.toString()));
        return body.appendChild(script).write();
    }

    private String requestForToken(MultiValueMap<String, String> params) {
        SimpleRequestWrapper<MultiValueMap<String, String>, String> wrapper = new SimpleRequestWrapper<>();
        wrapper.setBody(params)
               .setUrl(tokenURI)
               .setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> response = wrapper.exchange(HttpMethod.POST, String.class);
        return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
    }

    private String saveTokenRequest(String body) {
        SimpleRequestWrapper<String, String> wrapper = new SimpleRequestWrapper<>();
        wrapper.setBody(body)
               .setUrl(serviceSavePath)
               .setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = wrapper.exchange(HttpMethod.POST, String.class);
        return response.getStatusCode() == HttpStatus.OK
                ? String.valueOf(Objects.requireNonNull(response.getHeaders().get("Authorization")).get(0))
                : null;
    }
}
