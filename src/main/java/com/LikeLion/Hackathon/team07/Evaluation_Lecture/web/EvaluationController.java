package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Search;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.SearchService;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.EvaluationRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.EvaluationService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    private EvaluationRepository evaluationRepository;

    public EvaluationController(EvaluationService evaluationService){
        this.evaluationService = evaluationService;
    }

    @GetMapping("/")
    public String mainForm(){
        return "Search";
    }

    @GetMapping("/evaluation/search/{page}")
    public ResponseEntity<ResultDto> searchPro(@PathVariable int page, @RequestParam(value = "search") String search, @RequestParam(value = "searchType") String searchType, @RequestParam(value = "lectureDivide") String lectureDivide){
        System.out.println(search);
        List<EvaluationDto> searchList = evaluationService.getSearchList(search);
        System.out.println(searchList);

        PageRequest pageable = PageRequest.of(page, 10, Sort.by("created_at").descending());
        System.out.println(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(ResultDto.createResult(200, "검색 완료"));
    }

//    @GetMapping("/find-by-name")
//    public Page<ResultDto> findByName(@RequestParam(required = false, defaultValue = "0") int page) {
//        PageRequest pageable = PageRequest.of(page, 10, Sort.by("created_at").descending());
//        return evaluationRepository.findByTitleContains(search, pageable);
//    }

}
