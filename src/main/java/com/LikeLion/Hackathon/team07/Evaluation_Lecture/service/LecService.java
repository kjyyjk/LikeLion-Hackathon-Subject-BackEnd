package com.LikeLion.Hackathon.team07.Evaluation_Lecture.service;

import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Lec;
import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.LecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecService {
    @Autowired
    private LecRepository lecRepository;

    //평가글 작성
    public void write(Lec lec){
        lecRepository.save(lec);
    }


    //평가글 리스트
    public List<Lec> lecList(){
        return lecRepository.findAll();
    }

    //평가글 불러오기
    public Lec lecView(Integer id){
        return lecRepository.findById(id).get();
    }


    //평가글 삭제
    public void lecDelete(Integer id){
        lecRepository.deleteById(id);
    }

}
