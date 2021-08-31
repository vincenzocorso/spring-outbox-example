package it.vincenzocorso.todosservice.presentation.listener;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedMessagesRepository extends JpaRepository<ProcessedMessage, String> {
}
