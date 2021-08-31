package it.vincenzocorso.tasksservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoConfig {
	/**
	 * Enable transaction support for MongoDB (disabled by default).
	 *
	 * @see <a href="https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.transactions">Spring Data MongoDB Transactions</a>
	 */
	@Bean
	MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTransactionManager(mongoDatabaseFactory);
	}
}
