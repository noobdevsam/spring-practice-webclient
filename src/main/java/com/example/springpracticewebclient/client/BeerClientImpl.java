package com.example.springpracticewebclient.client;

import com.example.springpracticewebclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {
	
	private final WebClient webClient;
	public static final String BEER_PATH = "/api/v3/beer";
	private static final String BEER_PATH_ID = BEER_PATH + "/{id}";
	
	public BeerClientImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
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
	
	@Override
	public Mono<BeerDTO> getBeerById(String id) {
		return webClient.get()
			       .uri(
				       uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(id)
			       )
			       .retrieve()
			       .bodyToMono(BeerDTO.class);
	}
	
	@Override
	public Flux<BeerDTO> getBeerByStyle(String beerStyle) {
		return webClient.get()
			       .uri(
				       uriBuilder -> uriBuilder
					                     .path(BEER_PATH)
					                     .queryParam("beerStyle", beerStyle)
					                     .build()
			       )
			       .retrieve()
			       .bodyToFlux(BeerDTO.class);
	}
	
	@Override
	public Mono<BeerDTO> createBeer(BeerDTO beerDTO) {
		return webClient.post()
			       .uri(BEER_PATH)
			       .body(Mono.just(beerDTO), BeerDTO.class)
			       .retrieve()
			       .toBodilessEntity()
			       .flatMap(
				       voidResponseEntity -> Mono.just(voidResponseEntity.getHeaders().get("Location").getFirst())
			       )
			       .map(
				       path -> path.split("/")[path.split("/").length - 1]
			       ).flatMap(this::getBeerById);
	}
	
	@Override
	public Mono<BeerDTO> updateBeer(BeerDTO beerDTO) {
		return webClient.put()
			       .uri(
				       uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerDTO.id())
			       )
			       .body(Mono.just(beerDTO), BeerDTO.class)
			       .retrieve()
			       .toBodilessEntity()
			       .flatMap(
				       _ -> getBeerById(beerDTO.id())
			       );
	}
	
	@Override
	public Mono<BeerDTO> patchBeer(BeerDTO beerDTO) {
		return webClient.patch()
			       .uri(
				       uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerDTO.id())
			       )
			       .body(Mono.just(beerDTO), BeerDTO.class)
			       .retrieve()
			       .toBodilessEntity()
			       .flatMap(
				       _ -> getBeerById(beerDTO.id())
			       );
	}
	
	@Override
	public Mono<Void> deleteBeer(BeerDTO beerDTO) {
		return webClient.delete()
			       .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerDTO.id()))
			       .retrieve()
			       .toBodilessEntity()
			       .then();
	}
	
}