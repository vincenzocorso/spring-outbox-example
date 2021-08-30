package it.vincenzocorso.todosservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@Getter
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String text;

	public Todo(String title, String text) {
		this.title = title;
		this.text = text;
	}
}
