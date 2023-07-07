package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter //@Getter이 있어야 db에 null값이 아닌 값이 들어간다
public class UserJoinRequestDto {
    private String userID;
    private String userPassword;
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
