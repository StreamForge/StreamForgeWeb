package com.streamforge.http.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class SimpleRequestWrapper<B, E> extends HttpHeaders {

    private B body;
    private String url;

    public B getBody() {
        return body;
    }

    public SimpleRequestWrapper setBody(B body) {
        this.body = body;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SimpleRequestWrapper setUrl(String url) {
        this.url = url;
        return this;
    }

    public ResponseEntity<E> exchange(HttpMethod method, Class<E> returnType) {
        HttpEntity<B> entity = new HttpEntity<>(body, createHttpHeaders());
        return new RestTemplate().exchange(this.url, method, entity, returnType);
    }

    private HttpHeaders createHttpHeaders() {
        return this.entrySet().stream()
                .collect(Collector.of(HttpHeaders::new,
                        this::mergeHttpHeaders,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }

    private void mergeHttpHeaders(HttpHeaders httpHeaders, Map.Entry<String, List<String>> params) {
        httpHeaders.addAll(params.getKey(), params.getValue());
    }
}
