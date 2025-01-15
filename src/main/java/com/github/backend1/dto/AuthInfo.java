package com.github.backend1.dto;

import lombok.Getter;

@Getter
public class AuthInfo { //인증정보 담은 dto
    private Long memberId;
    //memberId를 멤버 변수로 삼은 AuthInfo
    private AuthInfo(Long memberId) {
        this.memberId = memberId;
    }

    public static AuthInfo of(Long memberId) {
        return new AuthInfo(memberId);
    }
}
