package com.example.jungeun.semiprojectv1.member;

import com.example.jungeun.semiprojectv1.domain.MemberDTO;
import com.example.jungeun.semiprojectv1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional // 롤백을 사용하기 위함
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MemberServiceTest {

    private final MemberService memberService;

    @Test
    @DisplayName("MemberService newMember test")
    public void newMemberTest() {
        // Given T or F 인지 알아보기
        MemberDTO dto = MemberDTO.builder()
                .userid("abc1234")
                .password("987xyz")
                .name("abc123")
                .email("abc123@gmail.com")
                .build();

        // When
        boolean result = memberService.newMember(dto);

        // Then
        assertTrue(result);
    }
}
