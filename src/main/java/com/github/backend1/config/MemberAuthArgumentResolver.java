package com.github.backend1.config;

import com.github.backend1.dto.AuthInfo;
import com.github.backend1.service.JwtService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

public class MemberAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;

    public MemberAuthArgumentResolver(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) { //어떤 대상에 바인딩할 것인지
        return AuthInfo.class.isAssignableFrom(parameter.getParameterType());
    } //앞에서 AuthInfo class에 대해 assign한다고 정의했기 때문에 위와 같이 작성

    @Override
    //어떤 argument를 넣어줄 것인지 정의
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory){
        String authorization = webRequest.getHeader("Authorization"); //Header에 JWT토큰 가져옴
        if(authorization == null || !authorization.startsWith("Bearer ")){ //Bearer 이후
            throw new RuntimeException("UnauthorizedException");
        }
        String token = authorization.substring(7);// 토큰만 가져와서
        Map<String, Long> decodedToken = jwtService.decode(token);
        //jwtService에서 디코드를 통해 Map 형태의 decodedToken이 나오게 됨.
        Long memberid = decodedToken.get(JwtService.CLAIM_NAME_MEMBER_ID);
        //해당 토큰에서 CLAIM_NAME_MEMBER_ID를 가져와서
        if(memberid == null){
            throw new RuntimeException("UnauthorizedException");
        } //가져온 memberId를 가지고 AuthInfo를 구현해서 리턴하면
        return AuthInfo.of(memberid); //파라미터에 바인딩
    }
}
