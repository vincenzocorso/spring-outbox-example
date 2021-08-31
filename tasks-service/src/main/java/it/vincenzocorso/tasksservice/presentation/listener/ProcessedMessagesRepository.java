package it.vincenzocorso.tasksservice.presentation.listener;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProcessedMessagesRepository extends MongoRepository<ProcessedMessage, String> {
}
