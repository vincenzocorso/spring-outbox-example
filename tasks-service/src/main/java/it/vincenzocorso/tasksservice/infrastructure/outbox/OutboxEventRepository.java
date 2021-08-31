package it.vincenzocorso.tasksservice.infrastructure.outbox;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutboxEventRepository extends MongoRepository<OutboxEvent, String> {
}
