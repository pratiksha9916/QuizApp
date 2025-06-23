package com.example.QuizApp.Service;

import com.example.QuizApp.Dao.QuestionWrapper;
import com.example.QuizApp.Dao.Questions;
import com.example.QuizApp.Dao.Quiz;
import com.example.QuizApp.Dao.Response;
import com.example.QuizApp.Repository.QuestionRepository;
import com.example.QuizApp.Repository.QuizRepository;
import com.example.QuizApp.ResponseStructure.ResponseStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepo;
    @Autowired
    QuestionRepository repository;
    Logger logger = LoggerFactory.getLogger(QuizService.class);

    public ResponseEntity<String> CreateQuiz(String category, int numQ, String title) {
        List<Questions> questions= repository.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        logger.info(" Create Quiz sucessfully: {}", quiz);
        return  new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
       Optional<Quiz> quiz= quizRepo.findById(id);
        List<Questions> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
        for(Questions q : questionsFromDB) {
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
      questionsForUser.add(qw);
        }

        logger.info(" Get Quiz: {}", quiz);
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizRepo.findById(id).get();
        List<Questions> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRight_answer()))
               right++;
                i++;
        }
        logger.info(" Calculate Quiz Result: {}", quiz);
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

