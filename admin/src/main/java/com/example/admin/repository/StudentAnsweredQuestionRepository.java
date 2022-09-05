package com.example.admin.repository;

import com.example.admin.entity.StudentAnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnsweredQuestionRepository extends JpaRepository<StudentAnsweredQuestion, Long> {
}
