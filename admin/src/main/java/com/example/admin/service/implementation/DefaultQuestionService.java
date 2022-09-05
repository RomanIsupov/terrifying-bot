package com.example.admin.service.implementation;

import com.example.admin.entity.AppliantAnsweredQuestion;
import com.example.admin.entity.Question;
import com.example.admin.entity.StudentAnsweredQuestion;
import com.example.admin.exception.NotFoundException;
import com.example.admin.repository.AppliantAnsweredQuestionRepository;
import com.example.admin.repository.QuestionRepository;
import com.example.admin.repository.StudentAnsweredQuestionRepository;
import com.example.admin.repository.UserRepository;
import com.example.admin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultQuestionService implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AppliantAnsweredQuestionRepository appliantAnsweredQuestionRepository;
	@Autowired
	StudentAnsweredQuestionRepository studentAnsweredQuestionRepository;

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public List<AppliantAnsweredQuestion> getAppliantAnsweredQuestions() {
		return appliantAnsweredQuestionRepository.findAll();
	}

	@Override
	public List<StudentAnsweredQuestion> getStudentAnsweredQuestions() {
		return studentAnsweredQuestionRepository.findAll();
	}

	@Override
	public void answerQuestion(Long questionId, String answerText) throws NotFoundException {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new NotFoundException("Question not found"));
		String role = userRepository.findByChat(question.getChat()).getRole();
		if (role.equals("appliant")) {
			AppliantAnsweredQuestion answeredQuestion = new AppliantAnsweredQuestion();
			answeredQuestion.setQuestionText(question.getText());
			answeredQuestion.setAnswerText(answerText);
			appliantAnsweredQuestionRepository.save(answeredQuestion);
		}
		else {
			StudentAnsweredQuestion answeredQuestion = new StudentAnsweredQuestion();
			answeredQuestion.setQuestionText(question.getText());
			answeredQuestion.setAnswerText(answerText);
			studentAnsweredQuestionRepository.save(answeredQuestion);
		}
		questionRepository.deleteById(questionId);
	}
}
