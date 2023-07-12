package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.LikeyService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LikeyController {
    @Autowired
    private LikeyService likeyService;

    public LikeyController(LikeyService likeyService) {
        this.likeyService = likeyService;
    }

    @PostMapping("/likey/{evaluationID}")
    public ResponseEntity<ResultDto> likeEvalutionPro(@Valid @RequestBody UserRequestDto requestDto, @PathVariable int evaluationID, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, bindingResult));
        }
        System.out.println(requestDto);
        System.out.println(evaluationID);

        int result = likeyService.saveLikey(evaluationID, requestDto);

        if(result == 0){
            System.out.println("추천 취소");
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.createResult(201, "추천 취소 완료"));
        } else {
            System.out.println("추천 성공");
            return ResponseEntity.status(HttpStatus.OK).body(ResultDto.createResult(200, "추천 성공"));
        }
    }

}
