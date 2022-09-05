package com.example.hits_bot.service;

import com.example.hits_bot.entity.AppliantAnsweredQuestion;
import com.example.hits_bot.entity.StudentAnsweredQuestion;

import java.util.List;

public interface QuestionService {

	void askQuestion(String chat, String text);

	List<AppliantAnsweredQuestion> getAppliantQuestions();

	List<StudentAnsweredQuestion> getStudentQuestions();
}
