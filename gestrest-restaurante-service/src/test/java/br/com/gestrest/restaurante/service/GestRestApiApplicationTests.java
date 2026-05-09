package br.com.gestrest.restaurante.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.gestrest.restaurante.service.infrastructure.GestrestRestauranteServiceApplication;

@SpringBootTest(classes = GestrestRestauranteServiceApplication.class)
@ActiveProfiles("test")
class GestrestRestauranteServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}