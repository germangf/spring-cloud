package ch.ggf.zuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2Config {

	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	@Value("${security.oauth2.client.accessTokenUri}")
	private String accessTokenUri;

	@LoadBalanced
	@Bean
  public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
 		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
 	}

	private OAuth2ProtectedResourceDetails resource() {
		ClientCredentialsResourceDetails resource  = new ClientCredentialsResourceDetails();
		resource.setClientId(clientId);
		resource.setAccessTokenUri(accessTokenUri);
		resource.setScope(Arrays.asList("read"));
		return resource;
	}

}
