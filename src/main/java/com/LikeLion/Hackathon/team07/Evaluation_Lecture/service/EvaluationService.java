package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Evaluation;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.EvaluationRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private static final int BLOCK_PAGE_NUM_COUNT =5; //블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT =4; //한 페이지에 존재하는 게시글 수

    @Transactional
    public List<EvaluationDto> getSearchList(String keyword){
        System.out.println("getSearchList 실행");
        List<Evaluation> evaluationList = evaluationRepository.findByTitleContaining(keyword);
        List<EvaluationDto> evaluationDtoList = new ArrayList<>();

        if(evaluationList.isEmpty()) return evaluationDtoList;

        for(Evaluation ch: evaluationList){
            evaluationDtoList.add(this.convertEntityToDto(ch));
        }

        return evaluationDtoList;
    }

    private EvaluationDto convertEntityToDto(Evaluation ch) {
        return EvaluationDto.builder()
                .userID(ch.getUserID())
                .lecture_name(ch.getLecture_name())
                .professor_name(ch.getProfessor_name())
                .lecture_year(ch.getLecture_year())
                .semester_divide(ch.getSemester_divide())
                .lecture_divide(ch.getLecture_divide())
                .evaluation_title(ch.getTitle())
                .evaluation_content(ch.getEvaluation_content())
                .build();
    }
}
