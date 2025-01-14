package com.github.backend1.service;

import com.github.backend1.domain.User;
import com.github.backend1.dto.SignUpRequest;
import com.github.backend1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String register(SignUpRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        MemberRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}

