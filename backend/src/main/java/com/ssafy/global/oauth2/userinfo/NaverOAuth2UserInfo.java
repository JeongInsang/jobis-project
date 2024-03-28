package com.ssafy.global.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {
    public NaverOAuth2UserInfo(Map<String, Object> attributes) { super(attributes); }

    @Override
    public String getId() { return getValueFromResponse("id"); }

    @Override
    public String getEmail() { return getValueFromResponse("email"); }

    @Override
    public String getNickname() { return getValueFromResponse("name"); }

    @Override
    public String getImageUrl() { return getValueFromResponse("profile_image"); }

    private String getValueFromResponse(String key) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response != null ? (String) response.get(key) : null;
    }
}
