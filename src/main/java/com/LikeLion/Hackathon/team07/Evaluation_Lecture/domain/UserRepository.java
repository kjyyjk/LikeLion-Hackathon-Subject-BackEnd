package com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserID(String userID);
}
