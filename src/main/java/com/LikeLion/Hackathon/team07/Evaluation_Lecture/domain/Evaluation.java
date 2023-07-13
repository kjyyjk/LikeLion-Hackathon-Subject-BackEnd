package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int evaluationID;

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

    @Column(nullable = false)
    private int likeCount;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Builder
    public Evaluation(String userID, String lectureName, String professorName, int lectureYear, String semesterDivide, String lectureDivide, String evaluationTitle, String evaluationContent, int likeCount, Timestamp createdAt, Timestamp updatedAt){
        this.userID = userID;
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.lectureYear = lectureYear;
        this.semesterDivide = semesterDivide;
        this.lectureDivide = lectureDivide;
        this.evaluationTitle = evaluationTitle;
        this.evaluationContent = evaluationContent;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
