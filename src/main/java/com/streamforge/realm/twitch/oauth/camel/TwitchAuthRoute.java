package com.streamforge.realm.twitch.oauth.camel;

import com.streamforge.realm.twitch.oauth.dtos.TwitchUserTokenDto;
import com.streamforge.realm.twitch.oauth.transformer.TwitchAuthDataTransformer;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.streamforge.realm.Constants.*;
import static org.apache.camel.builder.PredicateBuilder.and;

@Component
public class TwitchAuthRoute extends RouteBuilder {
    @Value("${streamforge.twitch.token-uri}")
    private String tokenURI;

    private TwitchHttpQueryBuilder twitchHttpQueryBuilder;

    @Autowired
    public TwitchAuthRoute(TwitchHttpQueryBuilder twitchHttpQueryBuilder) {
        this.twitchHttpQueryBuilder = twitchHttpQueryBuilder;
    }

    private final Consumer<Exchange> HTTP_QUERY_PARAMS_TO_MAP = exchange -> {
        Message in = exchange.getIn();
        String queryParams = (String) in.getHeader(Exchange.HTTP_QUERY);
        List<NameValuePair> mappedParams = new ArrayList<>();
        try {
            URIBuilder uriBuilder = new URIBuilder(Q_MARK + queryParams);
            mappedParams = uriBuilder.getQueryParams();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        in.setBody(mappedParams);
    };

    private final Consumer<Exchange> HTTP_POST_FOR_TOKENS = exchange -> {
        Message in = exchange.getIn();
        MultiValueMap<String, String> map = twitchHttpQueryBuilder.createTokenRequestURI();
        map.add("code", exchange.getIn().getBody(String.class));
        in.setBody(requestForToken(map));
    };

    @Override
    public void configure() throws Exception {
        rest(INTEGRATION_PATH)
                .get(AUTHENTICATION_PATH).to("direct:twitch");

        from("direct:twitch")
                .process(HTTP_QUERY_PARAMS_TO_MAP::accept)
                    .choice()
                        .when(body().method("isEmpty").contains(false))
                            .transform().method(TwitchAuthDataTransformer.class, TWITCH_ACCESS_CODE_DTO_METHOD_NAME)
                            .to("direct:twitch-integration");


        from("direct:twitch-integration")
                .choice()
                    .when(and(body().method("getCode").isNotNull(), body().method("getState").contains(STATE)))
                        .setBody(simple("${body.code}"))
                        .process(exchange -> {
                            MultiValueMap<String, String> map = twitchHttpQueryBuilder.createTokenRequestURI();
                            map.add(CODE_PROPERTY, exchange.getIn().getBody(String.class));
                            exchange.getIn().setBody(requestForToken(map));
                        })
                        .choice()
                            .when(body().isNotNull())
                                .unmarshal().json(JsonLibrary.Jackson, TwitchUserTokenDto.class);

    }

    private String requestForToken(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(tokenURI, HttpMethod.POST, entity, String.class);
        return (response.getStatusCode() == HttpStatus.OK)
                ? response.getBody()
                : null;
    }
}