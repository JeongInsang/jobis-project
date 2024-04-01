package com.ssafy.global.oauth2.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class RedirectUriStorage {

    private String redirectUri;

}
