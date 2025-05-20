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

}
