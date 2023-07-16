package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Evaluation;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.EvaluationRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private static final int PAGE_POST_COUNT = 3; //한 페이지에 존재하는 게시글 수


    @Transactional
    public Page<EvaluationDto> getEvaluationList(Pageable pageable, int pageNum, String search, String searchType, String lectureDivide){
        if(searchType.equals("최신순")){
            pageable = PageRequest.of(pageNum, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdAt"));
        } else if(searchType.equals("추천순")){
            pageable = PageRequest.of(pageNum, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "likeCount"));
        }

        Page<Evaluation> page;
        if(lectureDivide.equals("전체")){
            page = evaluationRepository.findByEvaluationContentContaining(search, pageable);
        } else {
            page = evaluationRepository.findByLectureDivideAndEvaluationContentContaining(lectureDivide, search, pageable);
        }

        System.out.println(page);
        Page<EvaluationDto> postPageList = page.map(
                evaluation -> new EvaluationDto(
                        evaluation.getUserID(),
                        evaluation.getLectureName(),
                        evaluation.getProfessorName(),
                        evaluation.getLectureYear(),
                        evaluation.getSemesterDivide(),
                        evaluation.getLectureDivide(),
                        evaluation.getEvaluationTitle(),
                        evaluation.getEvaluationContent(),
                        evaluation.getCreditScore(),
                        evaluation.getLectureScore(),
                        evaluation.getLikeCount(),
                        evaluation.getCreatedAt(),
                        evaluation.getUpdatedAt()
                )
        );

        return postPageList;
    }

    //평가글 작성
    public void write(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }


    //평가글 리스트
    public List<Evaluation> lecList(){
        return evaluationRepository.findAll();
    }

    //평가글 불러오기
    public Evaluation lecView(Integer evaluationID){
        return evaluationRepository.findById(evaluationID).get();
    }


    //평가글 삭제
    public void lecDelete(Integer evaluationID){
        evaluationRepository.deleteById(evaluationID);
    }

    private EvaluationDto convertEntityToDto(Evaluation ch) {
        return EvaluationDto.builder()
                .userID(ch.getUserID())
                .lectureName(ch.getLectureName())
                .professorName(ch.getProfessorName())
                .lectureYear(ch.getLectureYear())
                .semesterDivide(ch.getSemesterDivide())
                .lectureDivide(ch.getLectureDivide())
                .evaluationContent(ch.getEvaluationContent())
                .build();
    }
}
