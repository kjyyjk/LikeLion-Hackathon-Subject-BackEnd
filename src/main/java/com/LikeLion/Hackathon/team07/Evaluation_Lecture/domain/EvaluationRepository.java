package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EvaluationRepository extends PagingAndSortingRepository<Evaluation,Long> {
    List<Evaluation> findByEvaluationContentContains(String keyword);
    Page<Evaluation> findByEvaluationTitleContains(String keyword, Pageable pageable);

    Page<Evaluation> findAll(Pageable pageable);
}
