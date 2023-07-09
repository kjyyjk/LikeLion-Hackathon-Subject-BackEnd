package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    @Column
    private String userID;

    @Column
    private String userPassword;

    @Column
    private String userEmail;

    @Builder
    public User(String userID, String userPassword, String userEmail){
        this.userID = userID;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}
