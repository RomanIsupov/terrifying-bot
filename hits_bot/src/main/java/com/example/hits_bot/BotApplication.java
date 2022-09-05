package com.example.hits_bot;

import com.example.hits_bot.repository.AppliantAnsweredQuestionRepositoryImpl;
import com.example.hits_bot.repository.QuestionRepositoryImpl;
import com.example.hits_bot.repository.StudentAnsweredQuestionRepositoryImpl;
import com.example.hits_bot.repository.UserRepositoryImpl;
import com.example.hits_bot.service.QuestionServiceImpl;
import com.example.hits_bot.service.UserServiceImpl;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BotApplication {
	public static void main(String[] args) {
		try {
			Connection connection = connect();
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new Bot(
					new QuestionServiceImpl(
							new AppliantAnsweredQuestionRepositoryImpl(connection),
							new StudentAnsweredQuestionRepositoryImpl(connection),
							new QuestionRepositoryImpl(connection)),
					new UserServiceImpl(
							new UserRepositoryImpl(connection))));
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private static Connection connect() {
		String url = "jdbc:postgresql://localhost/hits_faq";
		String username = "romanisupov";
		String password = "password";

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
