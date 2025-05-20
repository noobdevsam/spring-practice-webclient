package com.example.springpracticewebclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {
	
	Flux<String> listBeers();
	
	Flux<Map> listBeersWithMap();
	
	Flux<JsonNode> listBeersWithJsonNode();
}
