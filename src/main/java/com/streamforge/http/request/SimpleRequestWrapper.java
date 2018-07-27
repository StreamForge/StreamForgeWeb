package com.streamforge.http.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SimpleRequestWrapper<B, E> extends HttpHeaders {
    private static final Supplier<RestTemplate> REST_TEMPLATE_SUPPLIER = RestTemplate::new;
    private static final Supplier<HttpHeaders> HTTP_HEADERS_SUPPLIER = HttpHeaders::new;
    private static final BiConsumer<HttpHeaders, Map.Entry<String, List<String>>> HEADERS_ENTRY_BI_CONSUMER = (header, entry) ->
            header.addAll(entry.getKey(), entry.getValue());

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
        return REST_TEMPLATE_SUPPLIER.get().exchange(this.url, method, entity, returnType);
    }

    private HttpHeaders createHttpHeaders() {
        return this.entrySet().stream()
                .collect(Collector.of(HTTP_HEADERS_SUPPLIER,
                        HEADERS_ENTRY_BI_CONSUMER,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
    }
}
