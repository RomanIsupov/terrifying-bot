package com.example.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
	private static final String BOT_TOKEN = "5356283799:AAF0uhyZmhhoMCLeAufRAuZu9Ta6LFz5pQE";
	private static final String BOT_NAME = "HITsFAQBot";


	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.hasMessage() && update.getMessage().hasText()) {
				Message inMessage = update.getMessage();
				String chatId = inMessage.getChatId().toString();

				String username = update.getChatMember().getFrom().getUserName();
				String response = username;
				SendMessage outMessage = new SendMessage();
				outMessage.setChatId(chatId);
				outMessage.setText(response);

				execute(outMessage);
			}
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private String parseMessage(Message message) {
		String response = "";
		String textMessage = message.getText();
		if (textMessage.equals("/start")) {

		}
		return response;
	}
}
