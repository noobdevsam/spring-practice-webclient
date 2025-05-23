package com.example.springpracticewebclient.client;

import com.example.springpracticewebclient.model.BeerDTO;
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
	
	@Test
	void test_get_by_id() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.listBeersWithDTO()
			.flatMap(beerDTO -> beerClient.getBeerById(beerDTO.id()))
			.subscribe(byIdDTO -> {
				System.out.println(byIdDTO.beerName());
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void test_get_by_style() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.getBeerByStyle("IPA").subscribe(response -> {
			System.out.println(response.toString());
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void test_create_beer() {
		var atomicBoolean = new AtomicBoolean(false);
		
		beerClient.createBeer(
				new BeerDTO("Test Beer", "Test Brewery", "54654354", "123456789", "12.99")
			)
			.subscribe(response -> {
				System.out.println(response.toString());
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);
	}
	
}