package com.ssafy.wp.model.dto.member;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String email;
    private String password;
    private String role;
    private String nickname;
    private LocalDate age;
    private String gender;
    private String profile;
}