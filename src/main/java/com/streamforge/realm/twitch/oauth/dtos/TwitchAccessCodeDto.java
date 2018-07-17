package com.streamforge.realm.twitch.oauth.dtos;

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

    public TwitchAccessCodeDto setCode(String code) {
        this.code = code;
        return this;
    }

    public TwitchAccessCodeDto setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public TwitchAccessCodeDto setState(String state) {
        this.state = state;
        return this;
    }
}
