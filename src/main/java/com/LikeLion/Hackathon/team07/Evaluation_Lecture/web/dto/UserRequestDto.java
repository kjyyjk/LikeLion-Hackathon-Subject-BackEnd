package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter

public class UserRequestDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userID;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String userPassword;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @Builder
    public UserRequestDto(String userID, String userPassword, String userEmail) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public User toEntity(){
        return User.builder()
                .userID(userID)
                .userPassword(userPassword)
                .userEmail(userEmail)
                .build();
    }
}
