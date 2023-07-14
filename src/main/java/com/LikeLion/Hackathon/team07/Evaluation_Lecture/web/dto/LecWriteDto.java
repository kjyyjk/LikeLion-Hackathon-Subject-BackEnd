package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import lombok.Getter;

@Getter
public class LecWriteDto {
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

    public LecWriteDto() {
    }

    public LecWriteDto(String userID, String lectureName, String professorName, String evaluationContent, Integer lectureYear, String semesterDivide, String lectureDivide, String evaluationTitle, String creditScore, String lectureScore, String createdAt, String updatedAt) {
        this.userID = userID;
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.evaluationContent = evaluationContent;
        this.lectureYear = lectureYear;
        this.semesterDivide = semesterDivide;
        this.lectureDivide = lectureDivide;
        this.evaluationTitle = evaluationTitle;
        this.creditScore = creditScore;
        this.lectureScore = lectureScore;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}

