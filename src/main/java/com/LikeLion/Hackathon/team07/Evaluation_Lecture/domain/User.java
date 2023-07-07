package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column
    private String user_id;

    @Column
    private String user_password;

    @Column
    private String user_email;

    @Builder
    public User(String userID, String userPassword, String userEmail){
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_email = user_email;
    }
}
