package com.example.QuizApp.service;

import com.example.QuizApp.dao.Questions;
import com.example.QuizApp.exception.QuestionNotFoundForUpdateException;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.exception.CategoryNotFoundException;
import com.example.QuizApp.exception.QuestionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
@Autowired
    QuestionRepository repo;
    Logger logger = LoggerFactory.getLogger(QuestionService.class);
//Add
public Questions addQuestions(Questions questions){
    logger.info("Adding new question: {}", questions.getQuestion_title());
    return repo.save(questions);
}

//Get all questions
public List<Questions> getQuestions(){
    logger.info("Fetching all questions");
    return repo.findAll();
}

//Get all by category
public List<Questions> getQuestionsByCategory(String category){
    logger.info("Fetching questions by category: {}", category);
    List<Questions> questions = repo.findByCategory(category);
    if (questions.isEmpty()) {
        throw new CategoryNotFoundException("No questions found for category: " + category);
    }
    return questions;
}

//update
    public Questions updateQuestions(int id, Questions questions){
        logger.info("Attempting to update question with ID: {}", id);
    Questions questions1=repo.findById(id)
            .orElseThrow(() -> new QuestionNotFoundForUpdateException("Question with ID " + id + " not found"));
    questions1.setQuestion_title(questions.getQuestion_title());
    questions1.setOption1(questions.getOption1());
    questions1.setOption2(questions.getOption2());
    questions1.setOption3(questions.getOption3());
    questions1.setOption4(questions.getOption4());
    questions1.setRight_answer(questions.getRight_answer());
    questions1.setDifficulty_level(questions.getDifficulty_level());
    questions1.setCategory(questions.getCategory());

        logger.info("Updated question: {}", questions1);

    return questions1;

    }

//    public Boolean deleteQuestions(int id){
//    if(id!=0){
//        logger.info("Attempting to delete question with ID: {}", id);
//        repo.findById(id);
//
//        return true;
//    }
//    else {
//        logger.info("Attempting to delete question with ID: {}", id);
//        return false;
//    }
//    }
public boolean deleteQuestions(int id) {
    logger.info("Attempting to delete question with ID: {}", id);
    Optional<Questions> optional = repo.findById(id);
    if (optional.isPresent()) {
        repo.deleteById(id);
        logger.info("Successfully deleted question with ID: {}", id);
        return true;
    } else {
        logger.error("Question with ID {} not found for deletion", id);
        throw new QuestionNotFoundException("Question with ID " + id + " not found");
    }
}


    public Questions getQuestionsById(int id){
        logger.info("Fetching question with ID: {}", id);
    return  repo.findById(id).get();
    }
}
