package com.example.QuizApp.Controller;

import com.example.QuizApp.Dao.QuestionWrapper;
import com.example.QuizApp.Dao.Response;
import com.example.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
@Autowired
    QuizService service;
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
       return service.CreateQuiz(category, numQ, title);
    }
@GetMapping("/get/{id}")
@PreAuthorize("hasAuthority('SCOPE_readquiz')")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") int id){
     return service.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    @PreAuthorize("hasAuthority('SCOPE_updatequiz')")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
return service.calculateResult(id,responses);
    }
}
