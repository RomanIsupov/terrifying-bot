package com.example.bot.repository;

import com.example.bot.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

	List<Question> findAll();

	Optional<Question> findById(Long id);
}
