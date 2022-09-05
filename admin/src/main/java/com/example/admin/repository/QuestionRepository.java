package com.example.admin.repository;

import com.example.admin.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Override
	List<Question> findAll();

	Optional<Question> findById(Long id);
}
