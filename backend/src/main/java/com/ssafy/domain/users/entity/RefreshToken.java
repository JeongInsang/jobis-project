package com.ssafy.domain.users.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash(value = "refreshToken")
@Builder
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    @TimeToLive
    private Long refreshTokenExpired;

    @Builder
    public RefreshToken(String id, String refreshToken, Long refreshTokenExpired){
        this.id = id;
        this.refreshToken = refreshToken;
        this.refreshTokenExpired = refreshTokenExpired;
    }

}
