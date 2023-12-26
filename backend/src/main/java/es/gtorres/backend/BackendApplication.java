package es.gtorres.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BackendApplication {

	@Value("${CORS_ORIGIN}")
	private String corsOrigin;

	@Value("${CORS_METHOD}")
	private String corsMethod;

	@Value("${CORS_HEADER}")
	private String corsHeader;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
