package com.example.springpracticewebclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {
	
	@Autowired
	BeerClient beerClient;
	
	@Test
	void test_list_beers() {
		
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.listBeers().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void test_get_map() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.listBeersWithMap().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void test_get_json_node() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.listBeersWithJsonNode().subscribe(response -> {
			System.out.println(response.toPrettyString());
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void test_get_dto() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.listBeersWithDTO().subscribe(response -> {
			System.out.println(response.beerName());
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);
	}
	
}