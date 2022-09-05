package com.example.se_bot.bot;

import lombok.Getter;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class Bot extends TelegramLongPollingCommandBot {
	private final String BOT_NAME;
	private final String BOT_TOKEN;

	public Bot() {
		BOT_NAME = "";
		BOT_TOKEN = "";
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override
	public void processNonCommandUpdate(Update update) {

	}
}
