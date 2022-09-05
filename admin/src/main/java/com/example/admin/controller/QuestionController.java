package com.example.admin.controller;

import com.example.admin.entity.AppliantAnsweredQuestion;
import com.example.admin.entity.Question;
import com.example.admin.entity.StudentAnsweredQuestion;
import com.example.admin.exception.NotFoundException;
import com.example.admin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping
	public List<Question> getQuestions() {
		return questionService.getQuestions();
	}

	@GetMapping("/appliant")
	public List<AppliantAnsweredQuestion> getAppliantAnsweredQuestions() {
		return questionService.getAppliantAnsweredQuestions();
	}

	@GetMapping("/student")
	public List<StudentAnsweredQuestion> getStudentAnsweredQuestions() {
		return questionService.getStudentAnsweredQuestions();
	}

	@PostMapping("/{questionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void answerQuestion(@PathVariable Long questionId, @RequestParam String answerText) throws NotFoundException {
		questionService.answerQuestion(questionId, answerText);
	}
}
