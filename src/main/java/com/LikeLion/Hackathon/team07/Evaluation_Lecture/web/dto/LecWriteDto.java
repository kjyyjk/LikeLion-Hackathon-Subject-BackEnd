package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

public class LecWriteDto {
    private String userID;
    private String lectureName;
    private String professorName;
    private String evaluationContent;

    public LecWriteDto() {
    }

    public LecWriteDto(String userID, String lectureName, String professorName, String evaluationContent) {
        this.userID = userID;
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.evaluationContent = evaluationContent;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }
}
