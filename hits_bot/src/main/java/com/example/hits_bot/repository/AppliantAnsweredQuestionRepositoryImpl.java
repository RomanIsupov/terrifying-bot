package com.example.hits_bot.repository;

import com.example.hits_bot.entity.AppliantAnsweredQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppliantAnsweredQuestionRepositoryImpl implements AppliantAnsweredQuestionRepository {

	private final Connection connection;
	public AppliantAnsweredQuestionRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<AppliantAnsweredQuestion> getAll() {
		String sql = "SELECT * FROM appliant_answered_questions";
		List<AppliantAnsweredQuestion> appliantAnsweredQuestions = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				appliantAnsweredQuestions.add(new AppliantAnsweredQuestion(
						resultSet.getLong("id"),
						resultSet.getString("question_text"),
						resultSet.getString("answer_text")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appliantAnsweredQuestions;
	}
}
