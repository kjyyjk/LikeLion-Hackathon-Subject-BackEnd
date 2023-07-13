package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Lec {
    @Id //주요식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA에서 기본 키를 자동으로 생성할 때 사용하는 방법 중 하나
    private Integer evaluationID;
    private String userID;
    private String lectureName;
    private String professorName;
    private String evaluationContent;
    private Integer lectureYear;
    private String semesterDivide;
    private String lectureDivide;
    private String evaluationTitle;
    private String creditScore;
    private String lectureScore;
    private String createdAt;
    private String updatedAt;
}
