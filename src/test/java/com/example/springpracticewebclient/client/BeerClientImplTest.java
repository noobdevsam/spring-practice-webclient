package com.example.springpracticewebclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerClientImplTest {
	
	@Autowired
	BeerClient beerClient;
	
	@Test
	void test_list_beers() {
		beerClient.listBeers().subscribe(System.out::println);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}
}