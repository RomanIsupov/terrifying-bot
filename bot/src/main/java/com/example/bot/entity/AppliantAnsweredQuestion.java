package com.example.bot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "appliant_answered_questions")
@Data
public class AppliantAnsweredQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String questionText;

	private String answerText;
}
