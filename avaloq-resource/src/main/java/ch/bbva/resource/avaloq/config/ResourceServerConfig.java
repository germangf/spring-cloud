package ch.ggf.resource.avaloq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableResourceServer
public class ResourceServerConfig {

  @Value("${security.jwt.keyPair}")
  private String keyPair;

  @Value("${security.jwt.keyStore}")
  private String keystore;

  @Value("${security.jwt.keyStorePassword}")
  private String keystorePassword;

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtTokenEnhancer());
  }

  @Bean
  public JwtAccessTokenConverter jwtTokenEnhancer() {
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(keystore), keystorePassword.toCharArray());
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyPair));
    return converter;
  }

}
