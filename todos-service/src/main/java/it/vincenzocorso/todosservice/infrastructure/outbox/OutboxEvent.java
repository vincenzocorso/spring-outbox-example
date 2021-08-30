package it.vincenzocorso.todosservice.infrastructure.outbox;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@NoArgsConstructor
@Getter
@Setter
public class OutboxEvent {
	@Id
	@Column(name = "message_id")
	private String messageId;

	@Column(name = "channel")
	private String channel;

	@Column(name = "message_key")
	private String messageKey;

	@Column(name = "payload")
	private String payload;

	@Column(name = "headers")
	private String headers;

	public OutboxEvent(String channel, String messageKey, String payload) {
		this.messageId = UUID.randomUUID().toString();
		this.channel = channel;
		this.messageKey = messageKey;
		this.payload = payload;
	}
}
