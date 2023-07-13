package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter //@Getter이 있어야 db에 null값이 아닌 값이 들어간다

public class UserJoinRequestDto {

    private String user_id;
    private String user_password;
    private String user_email;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userID;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String userPassword;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @Builder
    public UserJoinRequestDto(String userID, String userPassword, String userEmail) {
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
