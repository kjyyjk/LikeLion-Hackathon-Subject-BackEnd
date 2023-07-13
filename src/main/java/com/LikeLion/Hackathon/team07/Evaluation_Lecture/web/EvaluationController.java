package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web;

//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Search;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.SearchService;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.EvaluationRepository;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.service.EvaluationService;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.EvaluationDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.ResultDto;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto.searchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
}
