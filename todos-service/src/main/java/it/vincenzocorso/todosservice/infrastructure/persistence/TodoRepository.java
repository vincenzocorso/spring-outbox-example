package it.vincenzocorso.todosservice.infrastructure.persistence;

import it.vincenzocorso.todosservice.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
