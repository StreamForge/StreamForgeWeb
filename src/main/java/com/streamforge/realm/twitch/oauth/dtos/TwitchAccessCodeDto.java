package com.streamforge.realm.twitch.oauth.dtos;

import org.apache.http.client.utils.URIBuilder;

public class TwitchAccessCodeDto {
    private String code;
    private String scope;
    private String state;

    public TwitchAccessCodeDto() { }

    public String getCode() {
        return code;
    }

    public String getScope() {
        return scope;
    }

    public String getState() {
        return state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setState(String state) {
        this.state = state;
    }
}
