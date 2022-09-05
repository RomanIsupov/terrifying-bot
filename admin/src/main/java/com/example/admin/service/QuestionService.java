package com.example.admin.service;

import com.example.admin.entity.AppliantAnsweredQuestion;
import com.example.admin.entity.Question;
import com.example.admin.entity.StudentAnsweredQuestion;
import com.example.admin.exception.NotFoundException;

import java.util.List;

public interface QuestionService {

	List<Question> getQuestions();

	List<AppliantAnsweredQuestion> getAppliantAnsweredQuestions();

	List<StudentAnsweredQuestion> getStudentAnsweredQuestions();

	void answerQuestion(Long questionId, String text) throws NotFoundException;
}
