package info.getsocial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MondoDbConfig {
	public @Bean MongoDbFactory getMongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("localhost", 27017), "getsocial");
	}
	
	public @Bean MongoTemplate getMongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
		return mongoTemplate;
	}
}
