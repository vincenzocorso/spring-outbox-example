package it.vincenzocorso.tasksservice.infrastructure.persistence;

import it.vincenzocorso.tasksservice.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
