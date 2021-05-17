package com.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import lombok.RequiredArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
@RequiredArgsConstructor
public class Question {

  @Id @GeneratedValue
  private  Long id;
  @Column(nullable = false) private String question;
  @Column(nullable = false) private String email;


	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

  public Question (String question, String email){
    super();
    this.question = question;
    this.email = email;
  }

}
