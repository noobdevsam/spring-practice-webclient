package com.example.springpracticewebclient.client;

import reactor.core.publisher.Flux;

public interface BeerClient {
	
	Flux<String> listBeers();
}
