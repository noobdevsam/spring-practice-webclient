package com.example.springpracticewebclient.model;

import java.time.LocalDateTime;

public record BeerDTO(
	String id,
	String version,
	String beerName,
	String beerStyle,
	String upc,
	String price,
	String quantityOnHand,
	LocalDateTime createdDate,
	LocalDateTime lastModifiedDate
) {
	
	public BeerDTO(String id, String beerName) {
		this(id, null, beerName, null, null, null, null, null, null);
	}
	
	public BeerDTO(String beerName, String beerStyle, String quantityOnHand, String upc, String price) {
		this(null, null, beerName, beerStyle, upc, price, quantityOnHand, null, null);
	}

}
