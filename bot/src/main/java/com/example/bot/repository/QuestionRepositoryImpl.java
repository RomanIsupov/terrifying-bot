package com.example.bot.repository;

import com.example.bot.entity.Question;

import java.util.List;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {
	@Override
	public List<Question> findAll() {
		return null;
	}

	@Override
	public Optional<Question> findById(Long id) {
		return Optional.empty();
	}
}
