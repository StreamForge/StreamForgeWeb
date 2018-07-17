package com.streamforge.realm.twitch.oauth.dtos;

import java.util.List;

public class TwitchUserTokenDto {
    private String access_token;
    private String refresh_token;
    private Long expires_in;
    private List<String> scope;

    public TwitchUserTokenDto() { }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public List<String> getScope() {
        return scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
