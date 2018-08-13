package ch.ggf.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServer {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "auth-server");
		SpringApplication.run(AuthServer.class, args);
	}

}
