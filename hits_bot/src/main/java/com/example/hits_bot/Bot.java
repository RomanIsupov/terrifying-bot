package com.example.hits_bot;

import com.example.hits_bot.service.QuestionService;
import com.example.hits_bot.service.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
	private static final String BOT_TOKEN = "5356283799:AAF0uhyZmhhoMCLeAufRAuZu9Ta6LFz5pQE";
	private static final String BOT_NAME = "HITsFAQBot";


	private ReplyKeyboardMarkup changeAndViewAndAsk;
	private ReplyKeyboardMarkup appliantAndStudent;
	private ReplyKeyboardMarkup backAndNext;
	private ReplyKeyboardMarkup back;

	private static Boolean isTypingQuestion = false;
	private static Boolean isEnteringRole = false;
	private static Boolean isViewingQuestions = false;

	private static Integer questionsAmount = 0;
	private static Integer currentQuestion = 0;

	private final QuestionService questionService;
	private final UserService userService;

	public Bot(QuestionService questionService, UserService userService) {
		this.questionService = questionService;
		this.userService = userService;

		initBack();
		initBackAndNext();
		initAppliantAndStudent();
		initChangeAndViewAndAsk();
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
	public void onUpdateReceived(Update update) {
		try {
			if (update.hasMessage() && update.getMessage().hasText()) {
				Message inMessage = update.getMessage();
				String chatId = inMessage.getChatId().toString();
				String messageText = inMessage.getText();

				SendMessage outMessage = new SendMessage();
				outMessage.setChatId(chatId);
				String response = "";

				if (isTypingQuestion) {
					isTypingQuestion = false;
					if (messageText.equals("Back")) {
						response = "Your question is not saved.";
						outMessage.setReplyMarkup(changeAndViewAndAsk);
					}
					else {
						questionService.askQuestion(chatId, messageText);
						response = "Your question is saved.\n" +
								"Please, wait for our administrator to answer it.";
						outMessage.setReplyMarkup(changeAndViewAndAsk);
					}
				}
				else if (isEnteringRole) {
					if (messageText.equals("Appliant") || messageText.equals("Student")) {
						isEnteringRole = false;
						userService.changeRole(chatId, messageText.toLowerCase());
						response = "Your role is updated!";
						outMessage.setReplyMarkup(changeAndViewAndAsk);
					}
					else {
						response = "Incorrect role. Please, choose one of the next two:\n" +
								"Appliant or Student.";
						outMessage.setReplyMarkup(appliantAndStudent);
					}
				}
				else if (isViewingQuestions) {
					if (messageText.equals("Back")) {
						isViewingQuestions = false;
						response = "Please, choose an action.";
						outMessage.setReplyMarkup(changeAndViewAndAsk);
					}
					else if (messageText.equals("Next")) {
						String role = userService.getRole(chatId);
						if (role.equals("appliant")) {
							var questions = questionService.getAppliantQuestions();
							questionsAmount = questions.size();
							response = "Question #" + currentQuestion + "\n" +
									questions.get(currentQuestion).getQuestionText() + "\n" +
									questions.get(currentQuestion).getAnswerText();
							currentQuestion = (currentQuestion + 1) % questionsAmount;
							outMessage.setReplyMarkup(backAndNext);
						}
						else {
							var questions = questionService.getStudentQuestions();
							questionsAmount = questions.size();
							response = "Question #" + currentQuestion + "\n" +
									questions.get(currentQuestion).getQuestionText() + "\n" +
									questions.get(currentQuestion).getAnswerText();
							currentQuestion = (currentQuestion + 1) % questionsAmount;
							outMessage.setReplyMarkup(backAndNext);
						}
					}
					else {
						response = "Incorrect action. Please, choose one of the actions below";
						outMessage.setReplyMarkup(backAndNext);
					}
				}
				else if (messageText.equals("/start")) {
					userService.changeRole(chatId, "appliant");
					response = "Hello! This is HITs FAQ Bot.\n" +
							"You are `appliant` now.\n" +
							"Please, change your role if you're student already.";
					outMessage.setReplyMarkup(changeAndViewAndAsk);
				}
				else if (messageText.equals("Change role")) {
					isEnteringRole = true;
					response = "Please, choose your role.";
					outMessage.setReplyMarkup(appliantAndStudent);
				}
				else if (messageText.equals("Ask question")) {
					isTypingQuestion = true;
					response = "Please, type your question here, so our Administrator could answer it as soon as possible.";
					outMessage.setReplyMarkup(back);
				}
				else if (messageText.equals("View questions")) {
					currentQuestion = 0;
					String role = userService.getRole(chatId);
					if (role.equals("appliant")) {
						var questions = questionService.getAppliantQuestions();
						questionsAmount = questions.size();
						if (questions.isEmpty()) {
							response = "No questions yet.\n" +
									"Be the first one to ask!";
							outMessage.setReplyMarkup(changeAndViewAndAsk);
						}
						else {
							isViewingQuestions = true;
							response = "Question #" + currentQuestion + "\n" +
									questions.get(currentQuestion).getQuestionText() + "\n" +
									questions.get(currentQuestion).getAnswerText();
							currentQuestion = (currentQuestion + 1) % questionsAmount;
							outMessage.setReplyMarkup(backAndNext);
						}
					}
					else {
						var questions = questionService.getStudentQuestions();
						questionsAmount = questions.size();
						if (questions.isEmpty()) {
							response = "No questions yet.\n" +
									"Be the first one to ask!";
							outMessage.setReplyMarkup(changeAndViewAndAsk);
						}
						else {
							isViewingQuestions = true;
							response = "Question #" + currentQuestion + "\n" +
									questions.get(currentQuestion).getQuestionText() + "\n" +
									questions.get(currentQuestion).getAnswerText();
							currentQuestion = (currentQuestion + 1) % questionsAmount;
							outMessage.setReplyMarkup(backAndNext);
						}
					}
				}


				outMessage.setText(response);
				execute(outMessage);
			}
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private void initChangeAndViewAndAsk() {
		changeAndViewAndAsk = new ReplyKeyboardMarkup();
		changeAndViewAndAsk.setResizeKeyboard(true);
		changeAndViewAndAsk.setOneTimeKeyboard(false);

		ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();
		keyboardRow.add(new KeyboardButton("Change role"));
		keyboardRow.add(new KeyboardButton("View questions"));
		keyboardRow.add(new KeyboardButton("Ask question"));
		keyboardRows.add(keyboardRow);

		changeAndViewAndAsk.setKeyboard(keyboardRows);
	}

	private void initAppliantAndStudent() {
		appliantAndStudent = new ReplyKeyboardMarkup();
		appliantAndStudent.setResizeKeyboard(true);
		appliantAndStudent.setOneTimeKeyboard(false);

		ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();
		keyboardRow.add(new KeyboardButton("Appliant"));
		keyboardRow.add(new KeyboardButton("Student"));
		keyboardRows.add(keyboardRow);

		appliantAndStudent.setKeyboard(keyboardRows);
	}

	private void initBackAndNext() {
		backAndNext = new ReplyKeyboardMarkup();
		backAndNext.setResizeKeyboard(true);
		backAndNext.setOneTimeKeyboard(false);

		ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();
		keyboardRow.add(new KeyboardButton("Back"));
		keyboardRow.add(new KeyboardButton("Next"));
		keyboardRows.add(keyboardRow);

		backAndNext.setKeyboard(keyboardRows);
	}

	private void initBack() {
		back = new ReplyKeyboardMarkup();
		back.setResizeKeyboard(true);
		back.setOneTimeKeyboard(false);

		ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();
		keyboardRow.add(new KeyboardButton("Back"));
		keyboardRows.add(keyboardRow);

		back.setKeyboard(keyboardRows);
	}
}
