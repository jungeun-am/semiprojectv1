package com.example.jungeun.semiprojectv1.controller;

import com.example.jungeun.semiprojectv1.domain.MemberDTO;
import com.example.jungeun.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // 생성자 주입
/** 스프링 의존성 주입 방법
생성자 주입 (추천!) - 주입받을 필드는 final로 선언, @RequiredArgsConstructor 애노테이션 필요
setter 주입
필드 주입*/
public class MemberController {

    private final MemberService memberService; /*생성자 주입*/

    @GetMapping("/join")
    public String join() {
        return "views/member/join";
    }

    @PostMapping ("/join")
    public String joinok(MemberDTO member) {
        String returnPage = "redirect:/member/error";
        log.info("submit된 회원 정보:{}", member);

        if (memberService.newMember(member))
            returnPage = "redirect:/member/login";

        return returnPage;
    }

    @GetMapping("/login")
    public String login() {
        return "views/member/login";
    }

    @GetMapping("/myinfo")
    public String myinfo() {
        return "views/member/myinfo";
    }
}
