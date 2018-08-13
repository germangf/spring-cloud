package ch.ggf.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.ggf.zuul.filters.pre.HeadersFilter;
import ch.ggf.zuul.filters.pre.LogFilter;
import ch.ggf.zuul.filters.pre.OAuth2Filter;
import ch.ggf.zuul.filters.pre.SecretFilter;

@Configuration
public class FilterConfig {

	@Bean
	public LogFilter logFilter() {
		return new LogFilter();
	}

	@Bean
	public HeadersFilter headersFilter() {
		return new HeadersFilter();
	}

	@Bean
	public SecretFilter secretFilter() {
		return new SecretFilter();
	}

	@Bean
	public OAuth2Filter oAuth2Filter() {
		return new OAuth2Filter();
	}

}
