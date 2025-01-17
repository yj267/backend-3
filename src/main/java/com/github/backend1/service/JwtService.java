package com.github.backend1.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    @Value("supercoding")
    private String secretKey; //인코드와 디코드에 필요한 secretKey는 깃허브에 X

    public static final String CLAIM_NAME_MEMBER_ID = "memberId";
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    private void init(){
        algorithm = Algorithm.HMAC256(secretKey);
        jwtVerifier = JWT.require(algorithm).build();
    }

    public String encode(Long memberId) { //JWT를 발급하는 encode 메소드 정의
        LocalDateTime expiredAt = LocalDateTime.now().plusWeeks(4L);
        Date date = Timestamp.valueOf(expiredAt);

        return JWT.create()
                .withClaim(CLAIM_NAME_MEMBER_ID, memberId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public Map<String,Long> decode(String token){ //인코딩된 JWT를 디코딩하는 메소드 정의
        try{
            DecodedJWT jwt = jwtVerifier.verify(token);
            return Map.of(CLAIM_NAME_MEMBER_ID, jwt.getClaim(CLAIM_NAME_MEMBER_ID).asLong());
        } catch(JWTVerificationException e){
            log.warn("Failed to decode jwt.token: {}",token, e);
            return null;
        }
    }

}
