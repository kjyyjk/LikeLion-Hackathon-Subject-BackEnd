package com.LikeLion.Hackathon.team07.Evaluation_Lecture.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor()
@AllArgsConstructor
public class ResultDto {
    private int code;
    private Object result;
    public static ResultDto createSuccessResult(int code, String message){
        return new ResultDto(code, MessageField.ofSucc(message));
    }
    public static ResultDto createFailResult(int code, BindingResult bindingResult){
        return new ResultDto(code, MessageField.ofFail(bindingResult));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageField{
        private String message;
        public static MessageField ofSucc(String message){
            MessageField messageField = new MessageField();
            messageField.setMessage(message);
            return messageField;
        }

        public static List<MessageField> ofFail(BindingResult bindingResult) {
            List<MessageField> messageFields = bindingResult.getAllErrors().stream().map(error ->
                    new MessageField(((FieldError) error).getDefaultMessage())).collect(Collectors.toList());
            return messageFields;
        }
    }

}
