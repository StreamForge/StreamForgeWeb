package com.streamforge.realm.twitch.oauth.service;

public interface TwitchAuthService {
    String processCallback(String code, String status);
}
