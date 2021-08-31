package it.vincenzocorso.todosservice.presentation.web;

import it.vincenzocorso.todosservice.domain.DomainEvent;
import it.vincenzocorso.todosservice.domain.Todo;
import it.vincenzocorso.todosservice.domain.TodoCreatedEvent;
import it.vincenzocorso.todosservice.infrastructure.outbox.OutboxEventPublisher;
import it.vincenzocorso.todosservice.infrastructure.persistence.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoController {
	private final TodoRepository todoRepository;
	private final OutboxEventPublisher eventPublisher;

	@GetMapping
	public List<Todo> getTodos() {
		return this.todoRepository.findAll();
	}

	@PostMapping
	@Transactional
	public Todo createTodo() {
		String randomTitle = "title" + new Random().nextInt();
		String randomText = "text" + new Random().nextInt();

		Todo savedTodo = this.todoRepository.save(new Todo(randomTitle, randomText));

		DomainEvent domainEvent = new TodoCreatedEvent(savedTodo.getId().toString(), savedTodo.getTitle(), savedTodo.getText());
		this.eventPublisher.publish("TODO_EVENTS_CHANNEL", savedTodo.getId().toString(), domainEvent);

		return savedTodo;
	}
}
