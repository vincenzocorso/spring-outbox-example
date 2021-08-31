package it.vincenzocorso.tasksservice.presentation.web;

import it.vincenzocorso.tasksservice.domain.DomainEvent;
import it.vincenzocorso.tasksservice.domain.Task;
import it.vincenzocorso.tasksservice.domain.TaskCreatedEvent;
import it.vincenzocorso.tasksservice.infrastructure.outbox.OutboxEventPublisher;
import it.vincenzocorso.tasksservice.infrastructure.persistence.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
	private final TaskRepository taskRepository;
	private final OutboxEventPublisher eventPublisher;

	@GetMapping
	public List<Task> getTasks() {
		return this.taskRepository.findAll();
	}

	@PostMapping
	@Transactional
	public Task createTask() {
		String randomTitle = "title" + new Random().nextInt();
		String randomJobDescription = "jobDescription" + new Random().nextInt();
		Boolean randomCompleted = new Random().nextBoolean();

		Task savedTask = this.taskRepository.save(new Task(randomTitle, randomJobDescription, randomCompleted));

		DomainEvent domainEvent = new TaskCreatedEvent(savedTask.getId(), savedTask.getTitle(), savedTask.getJobDescription(), savedTask.getCompleted());
		this.eventPublisher.publish("TASK_EVENTS_CHANNEL", savedTask.getId(), domainEvent);

		return savedTask;
	}
}
