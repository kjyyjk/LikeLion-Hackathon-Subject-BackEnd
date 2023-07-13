package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Likey;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class LikeyDto {
    private String userID;
    private int evaluationID;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    public LikeyDto(String userID, int evaluationID, Timestamp createdAt, Timestamp updatedAt) {
        this.userID = userID;
        this.evaluationID = evaluationID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Likey toEntity(){
        return Likey.builder()
                .userID(userID)
                .evaluationID(evaluationID)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
