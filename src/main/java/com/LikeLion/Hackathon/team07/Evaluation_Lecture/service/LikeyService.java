package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.*;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.LikeyDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeyService {
    private final LikeyRepository likeyRepository;
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Transactional
    public int checkUser(String userID){
        User user = userRepository.findByUserID(userID);

        // 유저가 존재하지 않는 경우
        if(user == null){
            return 1;
        } else{
            return 0;
        }
    }

    @Transactional
    public int saveLikey(int evaluationID, UserJoinRequestDto requestDto){
        Evaluation evaluationIDList = evaluationRepository.findByEvaluationID(evaluationID);
        List<Likey> likeyIDList = likeyRepository.findByEvaluationID(evaluationID);

        int likeCnt = evaluationIDList.getLikeCount();

        for (Likey ch : likeyIDList){
            System.out.println(requestDto.getUserID());
            System.out.println(ch.getUserID());

            // 추천한 적이 있을 경우
            if(ch.getUserID().equals(requestDto.getUserID())){
                likeyRepository.deleteById(ch.getLikeyIndex()); // 추천 정보 삭제
                evaluationIDList.setLikeCount(likeCnt - 1); // 추천 수 - 1
                return 0;
            }

        }
        // 추천한 적이 없을 경우
        LikeyDto likeyDto = new LikeyDto(requestDto.getUserID(), evaluationID, evaluationIDList.getCreatedAt(), evaluationIDList.getUpdatedAt() );
        System.out.println(likeyDto);
        likeyRepository.save(likeyDto.toEntity()).getLikeyIndex(); // 추천 정보 저장

        evaluationIDList.setLikeCount(likeCnt + 1); // 추천 수 + 1
        return 1;
    }

}
