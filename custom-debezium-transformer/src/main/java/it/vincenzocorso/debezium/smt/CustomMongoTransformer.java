package it.vincenzocorso.debezium.smt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.header.Headers;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom smt transformer for the Debezium MongoDB connector.
 *
 * @author Vincenzo Corso
 */
public class CustomMongoTransformer<R extends ConnectRecord<R>> implements Transformation<R> {
	private static final Logger log = LoggerFactory.getLogger(CustomMongoTransformer.class.getName());
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public R apply(R sourceRecord) {
		Struct struct = (Struct)sourceRecord.value();
		String databaseOperation = struct.getString("op");

		if("c".equals(databaseOperation)) {
			String encodedAfter = struct.getString("after");
			try {
				JsonNode after = objectMapper.readTree(encodedAfter);

				String channel = after.get("channel").textValue();
				String messageKey = after.get("message_key").textValue();
				String payload = after.get("payload").textValue();
				String encodedHeaders = after.get("headers").textValue();

				Headers headers = sourceRecord.headers();
				try {
					Map<String, String> headersMap = objectMapper.readValue(encodedHeaders, new TypeReference<HashMap<String, String>>(){});
					for(Map.Entry<String, String> entry : headersMap.entrySet())
						headers.addString(entry.getKey(), entry.getValue());
				} catch(Exception ex) {
					log.error("Can't decode headers column: ", ex);
				}

				sourceRecord = sourceRecord.newRecord(
						channel,
						null,
						Schema.STRING_SCHEMA,
						messageKey,
						null,
						payload,
						sourceRecord.timestamp(),
						headers);
			} catch (Exception ex) {
				log.error("Can't decode the message payload: ", ex);
			}
		}

		return sourceRecord;
	}

	@Override
	public ConfigDef config() {
		return new ConfigDef();
	}

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> configs) {
	}
}
