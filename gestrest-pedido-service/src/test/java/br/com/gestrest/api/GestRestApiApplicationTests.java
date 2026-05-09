package br.com.gestrest.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.gestrest.pedido.service.infrastructure.GestRestApiApplication;

@SpringBootTest(classes = GestRestApiApplication.class)
@ActiveProfiles("test")
class GestRestApiApplicationTests {

	@Test
	void contextLoads() {
	}

}