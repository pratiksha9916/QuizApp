package com.example.QuizApp.exception;

public class QuestionNotFoundForUpdateException extends RuntimeException{
  public  QuestionNotFoundForUpdateException(String msg){
        super(msg);
    }
}
