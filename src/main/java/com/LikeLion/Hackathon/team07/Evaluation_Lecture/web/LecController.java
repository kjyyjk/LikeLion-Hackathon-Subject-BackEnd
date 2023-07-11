package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Lec;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.LecService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.LecWriteDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserJoinRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class LecController {
    private final LecService lecService;

    @Autowired
    public LecController(LecService lecService) {
        this.lecService = lecService;
    }

    @PostMapping("/evaluation/write")
    public ResponseEntity<?> writeLec(@RequestBody LecWriteDto lecWriteDto) {
        Lec lec = new Lec();
        lec.setUserID(lecWriteDto.getUserID());
        lec.setLectureName(lecWriteDto.getLectureName());
        lec.setProfessorName(lecWriteDto.getProfessorName());
        lec.setEvaluationContent(lecWriteDto.getEvaluationContent());

        lecService.write(lec);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //전체 보여주는 리스트
    @GetMapping("/evaluation/list")
    public ResponseEntity<List<Lec>> lecList() {
        List<Lec> lecList = lecService.lecList();
        return new ResponseEntity<>(lecList, HttpStatus.OK);
    }

    //게시물 하나하나 보여줌
    @GetMapping("/evaluation/view/{evaluationID}")
    public ResponseEntity<Lec> lecView(@PathVariable("evaluationID") Integer id) {
        Lec lec = lecService.lecView(id);
        return new ResponseEntity<>(lec, HttpStatus.OK);
    }

    //수정
    @PutMapping("/evaluation/modify/{evaluationID}")
    public ResponseEntity<?> modifyLec(@PathVariable("evaluationID") Integer evaluationID, @RequestBody LecWriteDto LecWriteDto, Principal principal) {
        Lec lec = lecService.lecView(evaluationID);
        //게시글이 없으면
        if (lec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 유저 정보 일치 여부 확인 + 비밀번호도 추가하기
        if (!lec.getUserID().equals(LecWriteDto.getUserID())){
            return new ResponseEntity<>("User information does not match", HttpStatus.UNAUTHORIZED);
        }

        lec.setUserID(LecWriteDto.getUserID());
        lec.setLectureName(LecWriteDto.getLectureName());
        lec.setProfessorName(LecWriteDto.getProfessorName());
        lec.setEvaluationContent(LecWriteDto.getEvaluationContent());

        lecService.write(lec);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/evaluation/delete/{evaluationID}")
    public ResponseEntity<?> lecDelete(@PathVariable("evaluationID") Integer evaluationID,  @RequestBody LecWriteDto lecWriteDto) {
        Lec lec = lecService.lecView(evaluationID);

        //게시글이 없으면
        if (lec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 유저 정보 일치 여부 확인
        if (!lec.getUserID().equals(lecWriteDto.getUserID())){
            return new ResponseEntity<>("User information does not match", HttpStatus.UNAUTHORIZED);
        }

        /* 아이디 비번 이메일까지
        // 유저 정보 일치 여부 확인
        if (!lec.getUserID().equals(lecWriteDto.getUserID()) ||
                !User.getUserPassword().equals(UserJoinRequestDto.getUserPassword()) ||
                !lec.getUserEmail().equals(lecWriteDto.getUserEmail())) {
            return new ResponseEntity<>("User information does not match", HttpStatus.UNAUTHORIZED);
        }
        */

        lecService.lecDelete(evaluationID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}