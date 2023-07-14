package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userID;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String userPassword;

    @Builder
    public UserRequestDto(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }
}
