package com.github.backend1.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.stream.DoubleStream;

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
            DecodeJWT jwt = jwtVerifier.verify(token);
            return Map.of(CLAIM_NAME_MEMBER_ID, jwt.getclaim(CLAIM_NAME_MEMBER_ID).asLong);
        } catch(JWTVerificationException e){
            log.warn("Failed to decode jwt.token: {}",token, e);
            return null;
        }
    }

    private static class JWT {
        public static DoubleStream.Builder require(Algorithm algorithm) {
        }

        public static Object create() {
        }
    }
}
