package com.example.QuizApp.Repository;

import com.example.QuizApp.Dao.Questions;
import com.example.QuizApp.Dao.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
//
//    @Query(value = "SELECT * FROM questions q Where q.category :category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
//List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
