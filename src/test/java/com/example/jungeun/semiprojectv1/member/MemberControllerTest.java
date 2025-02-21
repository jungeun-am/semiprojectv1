package com.example.jungeun.semiprojectv1.member;

import com.example.jungeun.semiprojectv1.controller.MemberController;
import com.example.jungeun.semiprojectv1.repository.MemberRepository;
import com.example.jungeun.semiprojectv1.service.MemberService;
import javassist.bytecode.annotation.MemberValue;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MemberControllerTest {

    private final MockMvc mockMvc;
    private final MemberRepository memberMapper;
    @Test
    @DisplayName("/join POST request test")
    public void login() throws Exception {

        // Given
        String userid = "abc1232";
        String password = "abc123";
        String name = "abc123";
        String email = "abc@gmail.com";

        // When
        mockMvc.perform(post("/member/join")
                .param("userid", userid)
                .param("password", password)
                .param("name",name)
                .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));

      /*  // Then
        Member member = memberMapper.findByUserid(userid);
        assertThat(member).isNull();
        assertThat(member.getMno()).isNull();
        assertThat(member.getRegdate()).isNull();
        assertThat(member.getName()).isEqulTo("abc123");
        assertThat(member.getEmail()).isEqulTo("abc@gmail.com");*/

    }
}
