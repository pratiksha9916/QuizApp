package com.example.QuizApp.repository;

import com.example.QuizApp.dao.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
//
//    @Query(value = "SELECT * FROM questions q Where q.category :category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
//List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
