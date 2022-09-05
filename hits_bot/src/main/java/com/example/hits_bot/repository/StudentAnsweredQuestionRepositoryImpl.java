package com.example.hits_bot.repository;

import com.example.hits_bot.entity.StudentAnsweredQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentAnsweredQuestionRepositoryImpl implements StudentAnsweredQuestionRepository {

	private final Connection connection;
	public StudentAnsweredQuestionRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<StudentAnsweredQuestion> getAll() {
		String sql = "SELECT * FROM student_answered_questions";
		List<StudentAnsweredQuestion> studentAnsweredQuestions = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				studentAnsweredQuestions.add(new StudentAnsweredQuestion(
						resultSet.getLong("id"),
						resultSet.getString("question_text"),
						resultSet.getString("answer_text")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentAnsweredQuestions;
	}
}
