package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.UserRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserRequestDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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


    @Transactional
    public boolean login(UserRequestDto requestDto) {
        Optional<User> byUserID = userRepository.findByUserID(requestDto.getUserID());
        if (byUserID.isPresent()) {
            if(!byUserID.get().isLoginStatus() && byUserID.get().getUserPassword().equals(requestDto.getUserPassword())){
                byUserID.get().userLogin();
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean logout(UserRequestDto requestDto) {
        Optional<User> byUserID = userRepository.findByUserID(requestDto.getUserID());
        if(byUserID.isPresent()){
            if(byUserID.get().isLoginStatus() && byUserID.get().getUserPassword().equals(requestDto.getUserPassword())){
                byUserID.get().userLogout();
                return true;
            }
        }
        return false;
    }

    public boolean withDraw(UserRequestDto requestDto) {
        Optional<User> byUserID = userRepository.findByUserID(requestDto.getUserID());
        if (byUserID.isPresent()) {
            if(byUserID.get().getUserPassword().equals(requestDto.getUserPassword())){
                userRepository.delete(byUserID.get());
                return true;
            }
        }
        return false;
    }
}
