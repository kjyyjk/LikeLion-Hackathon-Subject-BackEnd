package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter //@Getter이 있어야 db에 null값이 아닌 값이 들어간다
public class UserJoinRequestDto {
    private String user_id;
    private String user_password;
    private String user_email;

    @Builder
    public UserJoinRequestDto(String userID, String userPassword, String userEmail) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_email = user_email;
    }

    public User toEntity(){
        return User.builder()
                .userID(user_id)
                .userPassword(user_password)
                .userEmail(user_email)
                .build();
    }
}
