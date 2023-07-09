package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Lec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LecRepository extends JpaRepository<Lec, Integer> {
}
