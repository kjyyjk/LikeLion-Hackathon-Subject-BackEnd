package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserID(String userID);
}
