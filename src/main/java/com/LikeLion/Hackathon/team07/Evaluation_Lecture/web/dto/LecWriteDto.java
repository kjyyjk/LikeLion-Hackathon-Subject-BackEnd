package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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

}