package com.example.hits_bot.repository;

import com.example.hits_bot.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

	private static Long usersAmount = 0L;
	private final Connection connection;
	public UserRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public User findByChat(String chat) {
		User user = null;
		String sql = "SELECT * FROM users WHERE chat=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, chat);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User(
						resultSet.getLong("id"),
						resultSet.getString("chat"),
						resultSet.getString("role")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void create(String chat, String role) {
		String sql = "INSERT INTO users(id, chat, role) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, usersAmount);
			preparedStatement.setString(2, chat);
			preparedStatement.setString(3, role);
			usersAmount++;
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRole(String chat, String role) {
		String sql = "UPDATE users SET role = ? WHERE chat = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			preparedStatement.setString(2, chat);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
