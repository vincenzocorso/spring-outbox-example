package it.vincenzocorso.tasksservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Getter
public class Task {
	@Id
	private String id;
	private String title;
	private String jobDescription;
	private Boolean completed;

	public Task(String title, String jobDescription, Boolean completed) {
		this.title = title;
		this.jobDescription = jobDescription;
		this.completed = completed;
	}
}
