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
    private String lectureName;

    @Column(nullable = false)
    private String professorName;

    @Column(nullable = false)
    private int lectureYear;

    @Column(nullable = false)
    private String semesterDivide;

    @Column(nullable = false)
    private String lectureDivide;

    @Column(nullable = false)
    private String evaluationTitle;

    @Column(nullable = false)
    private String evaluationContent;

    @Builder
    public Evaluation(String title, String userID, String lectureName, String professorName, int lectureYear, String semesterDivide, String lectureDivide, String evaluationTitle, String evaluationContent){
        this.title = title;
        this.userID = userID;
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.lectureYear = lectureYear;
        this.semesterDivide = semesterDivide;
        this.lectureDivide = lectureDivide;
        this.evaluationTitle = evaluationTitle;
        this.evaluationContent = evaluationContent;
    }
}
