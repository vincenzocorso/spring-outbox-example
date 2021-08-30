package it.vincenzocorso.todosservice.presentation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class DomainEventListener {
	@KafkaListener(topics = "TODO_EVENTS_CHANNEL")
	public void listen(
			@Payload String payload,
			@Header("aggregate_id") String aggregateId,
			@Header("message_id") String messageId,
			@Header("type") String type) {
		log.info("Handling incoming message:\nheaders:\naggregate_id {}\nmessage_id: {}\ntype: {}\npayload: {}", aggregateId, messageId, type, payload);
	}
}
