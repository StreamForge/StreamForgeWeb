package com.streamforge.realm.twitch.oauth.transformer;

import com.streamforge.realm.twitch.oauth.dtos.TwitchAccessCodeDto;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.streamforge.realm.Constants.*;

public class TwitchAuthDataTransformer {
    private static final Supplier<TwitchAccessCodeDto> TWITCH_ACCESS_CODE_DTO_CONSUMER = TwitchAccessCodeDto::new;

    public static TwitchAccessCodeDto transformToTwitchAccessCodeDto(List<NameValuePair> queryParams) {
        Map<String, String> mappedQueryParams = queryParams.stream()
                .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        return TWITCH_ACCESS_CODE_DTO_CONSUMER.get()
                .setCode(mappedQueryParams.get(CODE_PROPERTY))
                .setScope(mappedQueryParams.get(SCOPE_PROPERTY))
                .setState(mappedQueryParams.get(STATE_PROPERTY));
    }
}
