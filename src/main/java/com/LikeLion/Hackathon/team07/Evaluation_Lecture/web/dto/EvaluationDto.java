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
    private String lecture_name;
    private String professor_name;
    private int lecture_year;
    private String semester_divide;
    private String lecture_divide;
    private String evaluation_title;
    private String evaluation_content;

    @Builder
    public EvaluationDto(String title,String userID, String lecture_name, String professor_name, int lecture_year, String semester_divide, String lecture_divide, String evaluation_title, String evaluation_content) {
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

    public Evaluation toEntity(){
        return Evaluation.builder()
                .title(title)
                .userID(userID)
                .lecture_name(lecture_name)
                .professor_name(professor_name)
                .lecture_year(lecture_year)
                .semester_divide(semester_divide)
                .lecture_divide(lecture_divide)
//                .evaluation_title(evaluation_title)
                .evaluation_content(evaluation_content)
                .build();
    }
}
