package com.example.springpracticewebclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig implements WebClientCustomizer {
	
	private final String baseUrl;
	private final ReactiveOAuth2AuthorizedClientManager authorizedClientManager;
	
	public WebClientConfig(
		@Value("${webclient.baseUrl}") String baseUrl,
		ReactiveOAuth2AuthorizedClientManager authorizedClientManager
	) {
		this.baseUrl = baseUrl;
		this.authorizedClientManager = authorizedClientManager;
	}
	
	@Override
	public void customize(WebClient.Builder webClientBuilder) {
		
		var oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		oauth.setDefaultClientRegistrationId("springauth");
		
		webClientBuilder.filter(oauth).baseUrl(baseUrl);
	}
}
