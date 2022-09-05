package com.example.hits_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student_answered_questions")
@Data
@AllArgsConstructor
public class StudentAnsweredQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String questionText;

	private String answerText;
}
