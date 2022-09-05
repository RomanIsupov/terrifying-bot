package com.example.hits_bot.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionRepositoryImpl implements QuestionRepository {

	private static Long questionsAmount = 0L;

	private final Connection connection;
	public QuestionRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(String chat, String text) {
		String sql = "INSERT INTO questions(id, chat, text) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, questionsAmount);
			preparedStatement.setString(2, chat);
			preparedStatement.setString(3, text);
			questionsAmount++;
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
