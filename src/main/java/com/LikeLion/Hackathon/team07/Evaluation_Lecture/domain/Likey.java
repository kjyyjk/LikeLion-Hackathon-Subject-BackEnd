package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
public class Likey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeyIndex;

    @Column(nullable = false)
    private int evaluationID;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Builder
    public Likey(String userID, int evaluationID, Timestamp createdAt, Timestamp updatedAt){
        this.userID = userID;
        this.evaluationID = evaluationID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
