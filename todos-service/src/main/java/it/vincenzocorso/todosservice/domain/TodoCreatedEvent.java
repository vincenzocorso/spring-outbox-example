package it.vincenzocorso.todosservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TodoCreatedEvent implements DomainEvent {
	public final String todoId;
	public final String title;
	public final String text;

	@JsonIgnore
	@Override
	public String getType() {
		return "TODO_CREATED_EVENT";
	}
}
