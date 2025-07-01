package com.example.QuizApp.Service;

import com.example.QuizApp.Dao.Questions;
import com.example.QuizApp.Repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
@Autowired
    QuestionRepository repo;
    Logger logger = LoggerFactory.getLogger(QuestionService.class);
//Add
public Questions addQuestions(Questions questions){
    logger.info("Add New Quiz Question");
    return repo.save(questions);
}

//Get all questions
public List<Questions> getQuestions(){
    logger.info("Get All Question list");
    return repo.findAll();
}

//Get all by category
public List<Questions> getQuestionsByCategory(String category){
    logger.info("Get All Question list {}",category);
    return repo.findByCategory(category);
}

//update
    public Questions updateQuestions(int id, Questions questions){
    Questions questions1=repo.findById(id).get();
    questions1.setQuestion_title(questions.getQuestion_title());
    questions1.setOption1(questions.getOption1());
    questions1.setOption2(questions.getOption2());
    questions1.setOption3(questions.getOption3());
    questions1.setOption4(questions.getOption4());
    questions1.setRight_answer(questions.getRight_answer());
    questions1.setDifficulty_level(questions.getDifficulty_level());
    questions1.setCategory(questions.getCategory());

        logger.info("Updated Question: {}", questions1);

    return questions1;

    }

    public Boolean deleteQuestions(int id){
    if(id!=0){
        logger.info("Attempting to delete question with ID: {}", id);
        repo.findById(id);
     
        return true;
    }
    else {
        logger.info("Attempting to delete question with ID: {}", id);
        return false;
    }
    }

    public Questions getQuestionsById(int id){

    return  repo.findById(id).get();
    }
}
