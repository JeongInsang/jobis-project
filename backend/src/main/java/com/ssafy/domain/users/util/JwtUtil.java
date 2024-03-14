package com.ssafy.domain.users.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {


    public static String createJwt(String userName, String secretKey, Long expiredMs) {

        return Jwts.builder()
                .claim("userName", userName)
//                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.forName("HS256"), secretKey)
                .compact();
    }

}
