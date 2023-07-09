package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String lecture_name;

    @Column(nullable = false)
    private String professor_name;

    @Column(nullable = false)
    private int lecture_year;

    @Column(nullable = false)
    private String semester_divide;

    @Column(nullable = false)
    private String lecture_divide;

    @Column(nullable = false)
    private String evaluation_title;

    @Column(nullable = false)
    private String evaluation_content;

    @Builder
    public Evaluation(String title, String userID, String lecture_name, String professor_name, int lecture_year, String semester_divide, String lecture_divide, String evaluation_title, String evaluation_content){
        this.title = title;
        this.userID = userID;
        this.lecture_name = lecture_name;
        this.professor_name = professor_name;
        this.lecture_year = lecture_year;
        this.semester_divide = semester_divide;
        this.lecture_divide = lecture_divide;
        this.evaluation_title = evaluation_title;
        this.evaluation_content = evaluation_content;
    }
}
