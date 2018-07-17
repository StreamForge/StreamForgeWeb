package com.streamforge.realm.twitch.oauth.transformer;

import com.streamforge.realm.twitch.oauth.dtos.TwitchAccessCodeDto;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.streamforge.realm.Constants.*;

public class TwitchAuthDataTransformer {
    public static TwitchAccessCodeDto transformToTwitchAccessCodeDto(List<NameValuePair> queryParams) {
        Map<String, String> mappedQueryParams = queryParams.stream()
                .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        TwitchAccessCodeDto twitchAccessCodeDto = new TwitchAccessCodeDto();
        twitchAccessCodeDto.setCode(mappedQueryParams.get(CODE_PROPERTY));
        twitchAccessCodeDto.setScope(mappedQueryParams.get(SCOPE_PROPERTY));
        twitchAccessCodeDto.setState(mappedQueryParams.get(STATE_PROPERTY));
        return twitchAccessCodeDto;
    }
}
