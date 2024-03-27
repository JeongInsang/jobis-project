package com.ssafy.global.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getEmail() {
        return getAttribute(attributes, "kakao_account", "email");
    }

    @Override
    public String getNickname() {
        return getAttribute(attributes, "kakao_account.profile", "nickname");
    }

    @Override
    public String getImageUrl() {
        return getAttribute(attributes, "kakao_account.profile", "thumbnail_image_url");
    }

    private String getAttribute(Map<String, Object> attributes, String key, String subKey) {
        Map<String, Object> account = (Map<String, Object>) attributes.get(key);

        if (account != null) {
            Map<String, Object> subMap = (Map<String, Object>) account.get(subKey);
            if (subMap != null) {
                return (String) subMap.get(subKey);
            }
        }

        return null;
    }
}
