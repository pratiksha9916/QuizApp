package com.example.QuizApp.dao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionTitleValidator implements ConstraintValidator<ValidQuestionTitle, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        String lower = value.toLowerCase();
        return lower.contains("java") || lower.contains("python");
    }
}

