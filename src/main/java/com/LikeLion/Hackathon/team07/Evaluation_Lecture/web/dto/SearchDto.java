//package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;
//
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.Search;
//import com.LikeLion.Hackathon.team07.Evaluation_Lecture.domain.User;
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//public class SearchDto {
//    private int pageNum;
//    private String search;
//    private String searchType;
//    private String lectureDivide;
//
//    @Builder
//    public SearchDto(int pageNum, String search, String searchType, String lectureDivide){
//        this.pageNum = pageNum;
//        this.search = search;
//        this.searchType = searchType;
//        this.lectureDivide = lectureDivide;
//    }
//
//    public Search toEntity(){
//        return Search.builder()
//                .pageNum(pageNum)
//                .search(search)
//                .searchType(searchType)
//                .lectureDivide(lectureDivide)
//                .build();
//    }
//}
