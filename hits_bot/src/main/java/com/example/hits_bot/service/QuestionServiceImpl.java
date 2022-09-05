package com.example.hits_bot.service;

import com.example.hits_bot.entity.AppliantAnsweredQuestion;
import com.example.hits_bot.entity.StudentAnsweredQuestion;
import com.example.hits_bot.repository.AppliantAnsweredQuestionRepository;
import com.example.hits_bot.repository.QuestionRepository;
import com.example.hits_bot.repository.StudentAnsweredQuestionRepository;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

	private final AppliantAnsweredQuestionRepository appliantAnsweredQuestionRepository;
	private final StudentAnsweredQuestionRepository studentAnsweredQuestionRepository;
	private final QuestionRepository questionRepository;

	public QuestionServiceImpl(AppliantAnsweredQuestionRepository appliantAnsweredQuestionRepository,
							   StudentAnsweredQuestionRepository studentAnsweredQuestionRepository,
							   QuestionRepository questionRepository) {
		this.appliantAnsweredQuestionRepository = appliantAnsweredQuestionRepository;
		this.studentAnsweredQuestionRepository = studentAnsweredQuestionRepository;
		this.questionRepository = questionRepository;
	}

	@Override
	public void askQuestion(String chat, String text) {
		questionRepository.create(chat, text);
	}

	@Override
	public List<AppliantAnsweredQuestion> getAppliantQuestions() {
		return appliantAnsweredQuestionRepository.getAll();
	}

	@Override
	public List<StudentAnsweredQuestion> getStudentQuestions() {
		return studentAnsweredQuestionRepository.getAll();
	}
}
