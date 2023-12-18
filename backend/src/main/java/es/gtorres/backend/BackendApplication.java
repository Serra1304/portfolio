package es.gtorres.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

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

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin(corsOrigin);
		config.addAllowedMethod(corsMethod);
		config.addAllowedHeader(corsHeader);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
