package com.github.backend1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class AuthInfo { //인증정보 담은 dto
    private Long memberId; //memberId를 멤버 변수로 삼은 AuthInfo

}
