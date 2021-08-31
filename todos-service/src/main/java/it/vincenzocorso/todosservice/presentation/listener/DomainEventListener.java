package it.vincenzocorso.todosservice.presentation.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class DomainEventListener {
	private final ProcessedMessagesRepository processedMessagesRepository;

	@KafkaListener(topics = "TODO_EVENTS_CHANNEL")
	@Transactional
	public void listen(
			@Payload String payload,
			@Header("aggregate_id") String aggregateId,
			@Header("message_id") String messageId,
			@Header("type") String type) {
		log.info("Handling incoming message:\nheaders:\naggregate_id: {}\nmessage_id: {}\ntype: {}\npayload: {}", aggregateId, messageId, type, payload);

		Optional<ProcessedMessage> processedMessage = this.processedMessagesRepository.findById(messageId);
		if(processedMessage.isPresent()) {
			log.info("MESSAGE ALREADY PROCESSED ({})", messageId);
		} else {
			this.processedMessagesRepository.save(new ProcessedMessage(messageId));
			log.info("Calling business logic");
		}
	}
}
