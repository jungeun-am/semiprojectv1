package com.example.jungeun.semiprojectv1.controller;

import com.example.jungeun.semiprojectv1.domain.Member;
import com.example.jungeun.semiprojectv1.domain.MemberDTO;
import com.example.jungeun.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    // ResponseEntity는 스프링에서 HTTP와 관련된 기능을 구현할 때 사용
    // 상태코드, HTTP 헤더/본문등을 명시적으로 설정 가능
    @PostMapping ("/join")
    public ResponseEntity<?> joinok(MemberDTO member) {
        // 회원 가입 처리시 기타오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response= ResponseEntity.internalServerError().build();

        log.info("submit된 회원 정보:{}", member);

        try {
            // 정상 처리시 상태코드 200으로 응답
            memberService.newMember(member);
            response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            // 비정상 처리 시 상태코드 400으로 응답 - 클라이언트 잘못
            // 중복 아이디나 중복 이메일 사용시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리 시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/login")
    public String login() {
        return "views/member/login";
    }

    @GetMapping("/myinfo")
    public String myinfo(HttpSession session) {
        String returnUrl = "views/member/login";

        // 세션변수가 생성되어 있다면 myinfo로 이동가능
        if (session.getAttribute("loginUser") != null) {
            returnUrl = "views/member/myinfo";
        }
        return returnUrl ;
    }

    @PostMapping ("/login")
    public ResponseEntity<?> loginok(MemberDTO member, HttpSession session) {
        // 회원 가입 처리시 기타오류 발생에 대한 응답코드 설정
        ResponseEntity<?> response= ResponseEntity.internalServerError().build();

        log.info("submit된 로그인 정보:{}", member);

        try {
            // 정상 처리시 상태코드 200으로 응답
            Member loginUser = memberService.loginMember(member);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(600); // 세션 유지 : 10 분

            response = ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            // 비정상 처리 시 상태코드 400으로 응답 - 클라이언트 잘못
            // 아이디나 비밀번호 잘못 입력시
            response = ResponseEntity.badRequest().body(e.getMessage());
            e.printStackTrace();
        }catch (Exception e) {
            // 비정상 처리 시 상태코드 500으로 응답 - 서버 잘못
            e.printStackTrace();
        }
        return response;
    }

}

