package it.vincenzocorso.tasksservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskCreatedEvent implements DomainEvent {
	public final String taskId;
	public final String title;
	public final String jobDescription;
	public final Boolean completed;

	@JsonIgnore
	@Override
	public String getType() {
		return "TASK_CREATED_EVENT";
	}
}
