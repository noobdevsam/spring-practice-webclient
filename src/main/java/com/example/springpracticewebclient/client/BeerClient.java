package com.example.springpracticewebclient.client;

import com.example.springpracticewebclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {
	
	Flux<String> listBeers();
	
	Flux<Map> listBeersWithMap();
	
	Flux<JsonNode> listBeersWithJsonNode();
	
	Flux<BeerDTO> listBeersWithDTO();
	
	Mono<BeerDTO> getBeerById(String id);
}
