package com.example.QuizApp.dao;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = QuestionTitleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuestionTitle {
    String message() default "Question title must relate to 'java' or 'python'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

