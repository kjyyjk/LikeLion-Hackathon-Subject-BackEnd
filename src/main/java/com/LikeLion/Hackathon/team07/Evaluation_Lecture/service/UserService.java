package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.UserRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public int join(UserJoinRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getUserIndex();
    }

    public boolean checkDuplication(UserJoinRequestDto requestDto) {
        String checkUserID = requestDto.getUserID();
        String checkUserPassword = requestDto.getUserPassword();

        List<User> userList = userRepository.findAll();
        for(User user : userList){
            if (user.getUserID().equals(checkUserID) && user.getUserPassword().equals(checkUserPassword)){
                return true;
            }
        }
        return false;
    }
}
