package ch.ggf.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class GatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "gateway-application");
		SpringApplication.run(GatewayApplication.class, args);
	}

}
