package com.github.backend1.controller;

import com.github.backend1.dto.LoginRequest;
import com.github.backend1.dto.SignUpRequest;
import com.github.backend1.service.JwtService;
import com.github.backend1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request) {
        return memberService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        // Authentication logic to validate user and generate token
        return jwtService.encode(request.getEmail());
    }
}