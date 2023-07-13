package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor()
@AllArgsConstructor
public class searchResultDto {
    private int code;
    private Object result;

    public static searchResultDto createResult(int code, Page<EvaluationDto> evaluationResult) {
        return new searchResultDto(code, evaluationResult.getContent());
    }

    public static searchResultDto createResult(int code, BindingResult bindingResult) {
        return new searchResultDto(code, ResultDto.MessageField.of(bindingResult));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageField {
        private String message;

        public static searchResultDto.MessageField of(String message) {
            searchResultDto.MessageField messageField = new searchResultDto.MessageField();
            messageField.setMessage(message);
            return messageField;
        }


        public static List<searchResultDto.MessageField> of(BindingResult bindingResult) {
            List<searchResultDto.MessageField> messageFields = bindingResult.getAllErrors().stream().map(error ->
                    new searchResultDto.MessageField(((FieldError) error).getDefaultMessage())).collect(Collectors.toList());
            return messageFields;
        }
    }
}
