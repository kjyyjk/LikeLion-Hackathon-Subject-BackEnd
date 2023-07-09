package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Evaluation;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EvaluationDto {
    private String title;
    private String userID;
    private String lectureName;
    private String professorName;
    private int lectureYear;
    private String semesterDivide;
    private String lectureDivide;
    private String evaluationTitle;
    private String evaluationContent;

    @Builder
    public EvaluationDto(String title,String userID, String lectureName, String professorName, int lectureYear, String semesterDivide, String lectureDivide, String evaluationTitle, String evaluationContent) {
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

    public Evaluation toEntity(){
        return Evaluation.builder()
                .title(title)
                .userID(userID)
                .lectureName(lectureName)
                .professorName(professorName)
                .lectureYear(lectureYear)
                .semesterDivide(semesterDivide)
                .lectureDivide(lectureDivide)
                .evaluationTitle(evaluationTitle)
                .evaluationContent(evaluationContent)
                .build();
    }
}
