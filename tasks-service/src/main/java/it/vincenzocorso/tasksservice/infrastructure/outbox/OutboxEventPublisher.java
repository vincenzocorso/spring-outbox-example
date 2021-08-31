package it.vincenzocorso.tasksservice.infrastructure.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.vincenzocorso.tasksservice.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class OutboxEventPublisher {
	private final OutboxEventRepository outboxEventRepository;
	private final ObjectMapper objectMapper;

	public void publish(String channel, String aggregateId, DomainEvent event) {
		try {
			String payload = this.objectMapper.writeValueAsString(event);
			OutboxEvent outboxEvent = new OutboxEvent(channel, aggregateId, payload);

			Map<String, String> headers = new HashMap<>();
			headers.put("aggregate_id", aggregateId);
			headers.put("message_id", outboxEvent.getMessageId());
			headers.put("type", event.getType());
			String encodedHeaders = this.objectMapper.writeValueAsString(headers);
			outboxEvent.setHeaders(encodedHeaders);

			OutboxEvent savedOutboxEvent = this.outboxEventRepository.save(outboxEvent);
			this.outboxEventRepository.delete(savedOutboxEvent);
		} catch (Exception ex) {
			log.error("An error occurred during event publishing: ", ex);
			throw new RuntimeException(ex);
		}
	}
}
