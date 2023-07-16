package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Lec;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.UserRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.LecService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.UserService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.LecWriteDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class LecController {
    private final LecService lecService;
    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/evaluation/write")
    public ResponseEntity<ResultDto> writeLec(@RequestBody LecWriteDto lecWriteDto) {
        Lec lec = new Lec();
        Optional<User> user = userRepository.findByUserID(lecWriteDto.getUserID());

        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 작성해주세요"));
            }
        }

        lec.setUserID(lecWriteDto.getUserID());
        lec.setLectureName(lecWriteDto.getLectureName());
        lec.setProfessorName(lecWriteDto.getProfessorName());
        lec.setEvaluationContent(lecWriteDto.getEvaluationContent());
        lec.setEvaluationTitle(lecWriteDto.getEvaluationTitle());
        lec.setLectureYear(lecWriteDto.getLectureYear());
        lec.setSemesterDivide(lecWriteDto.getSemesterDivide());
        lec.setLectureDivide(lecWriteDto.getLectureDivide());
        lec.setCreditScore(lecWriteDto.getCreditScore());
        lec.setLectureScore(lecWriteDto.getLectureScore());

        lecService.write(lec);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 작성 완료"));
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
    public ResponseEntity<ResultDto> modifyLec(@PathVariable("evaluationID") Integer evaluationID, @RequestBody LecWriteDto lecWriteDto) {
        Lec lec = lecService.lecView(evaluationID);
        Optional<User> user = userRepository.findByUserID(lecWriteDto.getUserID());

        //게시글이 없으면
        if (lec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //로그인
        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 수정해주세요"));
            }
        }

        if (!lec.getUserID().equals(lecWriteDto.getUserID())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "유저정보가 일치하지 않습니다."));
        }

        // 유지할 기존 내용을 가져와서 업데이트
        String lectureName = lec.getLectureName();
        String professorName = lec.getProfessorName();
        String evaluationContent = lec.getEvaluationContent();
        String EvaluationTitle = lec.getEvaluationTitle();
        Integer LectureYear = lec.getLectureYear();
        String SemesterDivide = lec.getSemesterDivide();
        String LectureDivide = lec.getLectureDivide();
        String CreditScore = lec.getCreditScore();
        String LectureScore = lec.getLectureScore();

        // 수정할때 새로 쓴부분만 바뀌도록 (원래 데이터 유지)
        lec.setLectureName(lecWriteDto.getLectureName() != null ? lecWriteDto.getLectureName() : lectureName);
        lec.setProfessorName(lecWriteDto.getProfessorName() != null ? lecWriteDto.getProfessorName() : professorName);
        lec.setEvaluationContent(lecWriteDto.getEvaluationContent() != null ? lecWriteDto.getEvaluationContent() : evaluationContent);
        lec.setEvaluationTitle(lecWriteDto.getEvaluationTitle() != null ? lecWriteDto.getEvaluationTitle() : EvaluationTitle);
        lec.setLectureYear(lecWriteDto.getLectureYear() != null ? lecWriteDto.getLectureYear() : LectureYear);
        lec.setSemesterDivide(lecWriteDto.getSemesterDivide() != null ? lecWriteDto.getSemesterDivide() : SemesterDivide);
        lec.setLectureDivide(lecWriteDto.getLectureDivide() != null ? lecWriteDto.getLectureDivide() : LectureDivide);
        lec.setCreditScore(lecWriteDto.getCreditScore() != null ? lecWriteDto.getCreditScore() : CreditScore);
        lec.setLectureScore(lecWriteDto.getLectureScore() != null ? lecWriteDto.getLectureScore() : LectureScore);


        lecService.write(lec);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 수정 완료"));

    }

    //삭제
    @DeleteMapping("/evaluation/delete/{evaluationID}")
    public ResponseEntity<?> lecDelete(@PathVariable("evaluationID") Integer evaluationID,  @RequestBody LecWriteDto lecWriteDto) {
        Lec lec = lecService.lecView(evaluationID);
        Optional<User> user = userRepository.findByUserID(lecWriteDto.getUserID());

        //게시글이 없으면
        if (lec == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //로그인되어있을때만 삭제가능
        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 삭제해주세요"));
            }
        }

        // 유저 정보 일치 여부 확인
        if (!lec.getUserID().equals(lecWriteDto.getUserID())){
            return new ResponseEntity<>("유저정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }


        lecService.lecDelete(evaluationID);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 삭제 완료"));

    }
}