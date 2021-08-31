package it.vincenzocorso.tasksservice.presentation.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "processed_messages")
@AllArgsConstructor
public class ProcessedMessage {
	@Id
	@Field(name = "message_id")
	private String messageId;
}
