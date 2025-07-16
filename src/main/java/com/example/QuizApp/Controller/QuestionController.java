package com.example.QuizApp.controller;

import com.example.QuizApp.dao.Questions;
import com.example.QuizApp.responseStructure.ResponseStructure;
import com.example.QuizApp.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class QuestionController {
    @Autowired
    QuestionService service;
    Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @PostMapping("/addQuestions")
//public Questions addNewQuestions(@RequestBody Questions questions){
//
//    return service.addQuestions(questions);
//}

    public ResponseEntity<ResponseStructure<Questions>> addNewQuestions( @Valid @RequestBody Questions questions) {
        logger.info("Adding new question: {}", questions.getQuestion_title());
        Questions addQuest = service.addQuestions(questions);
        ResponseStructure<Questions> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.OK.value());
        structure.setMsg("Add New Question Successfully");
        structure.setData(addQuest);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    @GetMapping("/allQuestions")
    @PreAuthorize("hasAuthority('SCOPE_readquestion')")
//    public List<Questions> getAllQuestions(Questions questions){
//        return service.getQuestions();
//    }
    public ResponseEntity<ResponseStructure<List<Questions>>> getAllQuestions(Questions questions) {
        logger.info("Fetching all questions");
        List<Questions> getQuest = service.getQuestions();
        ResponseStructure<List<Questions>> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.ACCEPTED.value());
        structure.setMsg("Get All Questions Successfully");
        structure.setData(getQuest);
        return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAuthority('SCOPE_readquestion')")
//    public List<Questions> getAllQuestionsbyCategory(@PathVariable("category") String category){
//        return service.getQuestionsByCategory(category);
//    }
    public ResponseEntity<ResponseStructure<List<Questions>>> getAllQuestionsbyCategory(@PathVariable("category") @NotBlank(message = "Category must not be blank") String category) {
        //validate input
        logger.info("Fetching questions by category: {}", category);
        List<Questions> getAllQuest = service.getQuestionsByCategory(category);
        ResponseStructure<List<Questions>> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.ACCEPTED.value());
        structure.setMsg("Get All Questions By Category Successfully");
        structure.setData(getAllQuest);
        return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateQuestions/{id}")
    @PreAuthorize("hasAuthority('SCOPE_updatequestion')")
//    public Questions updateQuestions(@PathVariable("id") int id, @RequestBody Questions qstn){
//    return service.updateQuestions(id, qstn);
//    }

    public ResponseEntity<ResponseStructure<Questions>> updateQuestions(@Valid @PathVariable("id") @Min(1) int id, @Valid @RequestBody Questions qstn) {
        logger.info("Updating question with ID: {} and title: {}", id, qstn.getQuestion_title());
        Questions updateQuest = service.updateQuestions(id, qstn);
        ResponseStructure<Questions> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.OK.value());
        structure.setMsg("Update Question Successfully");
        structure.setData(updateQuest);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }


    @DeleteMapping("/deleteQuestions/{id}")
    @PreAuthorize("hasAuthority('SCOPE_deletequestion')")
//    public Boolean deleteQuestionData(@PathVariable("id")int id){
//    return service.deleteQuestions(id);
//    }

    public ResponseEntity<ResponseStructure<Boolean>> deleteQuestionData(@PathVariable("id") @Min(1) int id) {
        logger.info("Deleting question with ID: {}", id);

        boolean deleteQuest = service.deleteQuestions(id);
        ResponseStructure<Boolean> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.OK.value());
        structure.setMsg("Delete Question Successfully");
        structure.setData(deleteQuest);
        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    @GetMapping("/questions/{id}")
    public Questions getQuestionsById(@PathVariable("id") int id) {
        logger.info("Fetching question by ID: {}", id);
        return service.getQuestionsById(id);
    }
}
