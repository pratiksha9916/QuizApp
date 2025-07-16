package com.example.QuizApp.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Question title is mandatory")
    @ValidQuestionTitle
    private String question_title;
    @NotBlank(message = "Option 1 is required")
    private String option1;
    @NotBlank(message = "Option 2 is required")
    private String option2;
    @NotBlank(message = "Option 3 is required")
    private String option3;
    @NotBlank(message = "Option 4 is required")
    private String option4;
    @NotBlank(message = "Correct answer is required")
    private  String right_answer;
    @NotBlank(message = "Difficulty level is required ")
    private String difficulty_level;
    @NotBlank(message = "Category is required")
    @Pattern(regexp = "java|python", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Category must be either 'java' or 'python'")
    private String category;

    @Transient
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Questions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", question_title='" + question_title + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", right_answer='" + right_answer + '\'' +
                ", difficulty_level='" + difficulty_level + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
