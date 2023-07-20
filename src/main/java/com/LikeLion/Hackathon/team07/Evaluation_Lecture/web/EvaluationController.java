package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Search;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.SearchService;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Evaluation;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.UserRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.EvaluationService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.searchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequiredArgsConstructor
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private UserRepository userRepository;

    public EvaluationController(EvaluationService evaluationService){
        this.evaluationService = evaluationService;
    }

    @GetMapping("/")
    public String mainForm(){
        return "Search";
    }

    @GetMapping("/evaluation/search/{page}")
    public ResponseEntity<searchResultDto> searchPro(@PathVariable int page, @RequestParam(value = "search") String search, @RequestParam(value = "searchType") String searchType, @RequestParam(value = "lectureDivide") String lectureDivide, Pageable pageable){
        if (searchType.isEmpty() || lectureDivide.isEmpty() || pageable == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        page = (page == 0) ? 0 : (page - 1);

        Page<EvaluationDto> postPageList = evaluationService.getEvaluationList(pageable, page, search, searchType, lectureDivide);

        System.out.println(postPageList);
        return ResponseEntity.status(HttpStatus.OK).body(searchResultDto.createResult(200, postPageList));
    }

    @PostMapping("/evaluation/write")
    public ResponseEntity<ResultDto> writeLec(@RequestBody EvaluationDto evaluationDto) {
        Evaluation evaluation = new Evaluation();
        System.out.println(evaluationDto.getUserID());
        Optional<User> user = userRepository.findByUserID(evaluationDto.getUserID());

        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 작성해주세요"));
            }
        }

        evaluation.setUserID(evaluationDto.getUserID());
        evaluation.setLectureName(evaluationDto.getLectureName());
        evaluation.setProfessorName(evaluationDto.getProfessorName());
        evaluation.setEvaluationContent(evaluationDto.getEvaluationContent());
        evaluation.setEvaluationTitle(evaluationDto.getEvaluationTitle());
        evaluation.setLectureYear(evaluationDto.getLectureYear());
        evaluation.setSemesterDivide(evaluationDto.getSemesterDivide());
        evaluation.setLectureDivide(evaluationDto.getLectureDivide());
        evaluation.setCreditScore(evaluationDto.getCreditScore());
        evaluation.setLectureScore(evaluationDto.getLectureScore());
        evaluation.setLikeCount(evaluationDto.getLikeCount());
        evaluation.setCreatedAt(evaluationDto.getCreatedAt());
        evaluation.setUpdatedAt(evaluationDto.getUpdatedAt());

        evaluationService.write(evaluation);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 작성 완료"));
    }

    //전체 보여주는 리스트
    @GetMapping("/evaluation/list")
    public ResponseEntity<List<Evaluation>> lecList() {
        List<Evaluation> lecList = evaluationService.lecList();
        return new ResponseEntity<>(lecList, HttpStatus.OK);
    }

    //게시물 하나하나 보여줌
    @GetMapping("/evaluation/view/{evaluationID}")
    public ResponseEntity<Evaluation> lecView(@PathVariable("evaluationID") Integer id) {
        Evaluation lec = evaluationService.lecView(id);
        return new ResponseEntity<>(lec, HttpStatus.OK);
    }

    //수정
    @PutMapping("/evaluation/modify/{evaluationID}")
    public ResponseEntity<ResultDto> modifyLec(@PathVariable("evaluationID") Integer evaluationID, @RequestBody EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationService.lecView(evaluationID);
        Optional<User> user = userRepository.findByUserID(evaluationDto.getUserID());

        //게시글이 없으면
        if (evaluation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //로그인
        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 수정해주세요"));
            }
        }

        if (!evaluation.getUserID().equals(evaluationDto.getUserID())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "유저정보가 일치하지 않습니다."));
        }

        // 유지할 기존 내용을 가져와서 업데이트
        String lectureName = evaluation.getLectureName();
        String professorName = evaluation.getProfessorName();
        String evaluationContent = evaluation.getEvaluationContent();
        String EvaluationTitle = evaluation.getEvaluationTitle();
        Integer LectureYear = evaluation.getLectureYear();
        String SemesterDivide = evaluation.getSemesterDivide();
        String LectureDivide = evaluation.getLectureDivide();
        String CreditScore = evaluation.getCreditScore();
        String LectureScore = evaluation.getLectureScore();

        // 수정할때 새로 쓴부분만 바뀌도록 (원래 데이터 유지)
        evaluation.setLectureName(evaluationDto.getLectureName() != null ? evaluationDto.getLectureName() : lectureName);
        evaluation.setProfessorName(evaluationDto.getProfessorName() != null ? evaluationDto.getProfessorName() : professorName);
        evaluation.setEvaluationContent(evaluationDto.getEvaluationContent() != null ? evaluationDto.getEvaluationContent() : evaluationContent);
        evaluation.setEvaluationTitle(evaluationDto.getEvaluationTitle() != null ? evaluationDto.getEvaluationTitle() : EvaluationTitle);
        evaluation.setLectureYear(evaluationDto.getLectureYear() != null ? evaluationDto.getLectureYear() : LectureYear);
        evaluation.setSemesterDivide(evaluationDto.getSemesterDivide() != null ? evaluationDto.getSemesterDivide() : SemesterDivide);
        evaluation.setLectureDivide(evaluationDto.getLectureDivide() != null ? evaluationDto.getLectureDivide() : LectureDivide);
        evaluation.setCreditScore(evaluationDto.getCreditScore() != null ? evaluationDto.getCreditScore() : CreditScore);
        evaluation.setLectureScore(evaluationDto.getLectureScore() != null ? evaluationDto.getLectureScore() : LectureScore);


        evaluationService.write(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 수정 완료"));

    }

    //삭제
    @DeleteMapping("/evaluation/delete/{evaluationID}")
    public ResponseEntity<?> lecDelete(@PathVariable("evaluationID") Integer evaluationID, @RequestBody EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationService.lecView(evaluationID);
        Optional<User> user = userRepository.findByUserID(evaluationDto.getUserID());

        //게시글이 없으면
        if (evaluation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //로그인되어있을때만 삭제가능
        if (user.isPresent()) {
            if (!(user.get().isLoginStatus())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResultDto.createResult(400, "로그인 후 삭제해주세요"));
            }
        }

        // 유저 정보 일치 여부 확인
        if (!evaluation.getUserID().equals(evaluationDto.getUserID())) {
            return new ResponseEntity<>("유저정보가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }


        evaluationService.lecDelete(evaluationID);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResultDto.createResult(200, "글 삭제 완료"));

    }
}
