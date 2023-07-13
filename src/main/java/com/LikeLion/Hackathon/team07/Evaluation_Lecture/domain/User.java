package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIndex;

    @Column
    private String userID;

    @Column
    private String userPassword;

    @Column
    private String userEmail;

    @Column
    private boolean loginStatus;

    public void userLogin(){
        this.loginStatus = true;
    }

    @Builder
    public User(String userID, String userPassword, String userEmail) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.loginStatus = false;
    }
}
