package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.UserService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String userJoinForm(){
        return "/userJoinForm";
    }

    @ResponseBody
    @PostMapping("/join")
    public Long userJoinPro(@RequestBody UserJoinRequestDto requestDto){
        return userService.join(requestDto);
    }
}
