package com.example.springpracticewebclient.client;

import com.example.springpracticewebclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {
	
	private final WebClient webClient;
	public static final String BEER_PATH = "/api/v3/beer";
	
	public BeerClientImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
	}
	
	@Override
	public Flux<String> listBeers() {
		return webClient.get()
			       .uri(BEER_PATH)
			       .retrieve()
			       .bodyToFlux(String.class);
	}
	
	@Override
	public Flux<Map> listBeersWithMap() {
		return webClient.get()
			       .uri(BEER_PATH)
			       .retrieve()
			       .bodyToFlux(Map.class);
	}
	
	@Override
	public Flux<JsonNode> listBeersWithJsonNode() {
		return webClient.get()
			       .uri(BEER_PATH)
			       .retrieve()
			       .bodyToFlux(JsonNode.class);
	}
	
	@Override
	public Flux<BeerDTO> listBeersWithDTO() {
		return webClient.get()
			       .uri(BEER_PATH)
			       .retrieve()
			       .bodyToFlux(BeerDTO.class);
	}
	
}
