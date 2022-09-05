package ru.romanisupov.controller;

import ru.romanisupov.entity.Question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/questions")
public class AdminController {

	@GetMapping
	public List<Question> getQuestions() {
		return null;
		//TODO complete method
	}

	@PostMapping
	public void answerQuestion(@Valid Question) {
		//TODO complete method
	}
}