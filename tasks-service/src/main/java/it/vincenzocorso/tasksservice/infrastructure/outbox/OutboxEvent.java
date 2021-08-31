package it.vincenzocorso.tasksservice.infrastructure.outbox;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "outbox")
@Getter
@Setter
public class OutboxEvent {
	@Id
	@Field(name = "message_id")
	private String messageId;

	@Field(name = "channel")
	private String channel;

	@Field(name = "message_key")
	private String messageKey;

	@Field(name = "payload")
	private String payload;

	@Field(name = "headers")
	private String headers;

	public OutboxEvent(String channel, String messageKey, String payload) {
		this.messageId = UUID.randomUUID().toString();
		this.channel = channel;
		this.messageKey = messageKey;
		this.payload = payload;
	}
}
