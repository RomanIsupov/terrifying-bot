package com.example.hits_bot.repository;


import com.example.hits_bot.entity.StudentAnsweredQuestion;

import java.util.List;

public interface StudentAnsweredQuestionRepository {
	List<StudentAnsweredQuestion> getAll();
}
