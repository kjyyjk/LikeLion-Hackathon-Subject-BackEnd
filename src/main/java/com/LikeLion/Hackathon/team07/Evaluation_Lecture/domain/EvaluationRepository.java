package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EvaluationRepository extends PagingAndSortingRepository<Evaluation,Integer> {
    Page<Evaluation> findByLectureDivideAndEvaluationContentContaining(String lectureDivide, String keyword, Pageable pageable);
    Page<Evaluation> findByEvaluationContentContaining(String keyword, Pageable pageable);
    Page<Evaluation> findAll(Pageable pageable);
    List<Evaluation> findAll();
    Evaluation findByEvaluationID(int evaluationID);
}
