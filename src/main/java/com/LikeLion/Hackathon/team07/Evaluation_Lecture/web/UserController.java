package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.UserService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String userJoinForm(){
        return "/userJoinForm";
    }

    @PostMapping("/join")
    public ResponseEntity<ResultDto> userJoinPro(@Valid @RequestBody UserJoinRequestDto requestDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400,bindingResult));
        }

        if(userService.checkDuplication(requestDto)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "회원가입 실패. 중복회원입니다."));
        }

        userService.join(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(ResultDto.createResult(200, "회원가입 완료"));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResultDto> processValidationError(BindingResult bindingResult){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createFailResult(400,bindingResult));
//    }
}
