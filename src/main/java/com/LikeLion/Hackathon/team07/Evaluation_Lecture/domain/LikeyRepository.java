package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.LikeyDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeyRepository extends JpaRepository<Likey, Integer> {
    List<Likey> findByEvaluationID(int evaluationID); // evaluationID로 글 검색
}
