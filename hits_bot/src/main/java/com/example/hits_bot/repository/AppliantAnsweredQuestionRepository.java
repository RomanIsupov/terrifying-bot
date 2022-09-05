package com.example.hits_bot.repository;

import com.example.hits_bot.entity.AppliantAnsweredQuestion;

import java.util.List;

public interface AppliantAnsweredQuestionRepository {
	List<AppliantAnsweredQuestion> getAll();
}
