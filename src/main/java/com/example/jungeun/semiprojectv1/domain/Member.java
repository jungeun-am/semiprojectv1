package com.example.jungeun.semiprojectv1.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // setter, getter, toString 자동생성
@Builder
public class Member {

    private int mno;
    private String userid;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regdate;


}
