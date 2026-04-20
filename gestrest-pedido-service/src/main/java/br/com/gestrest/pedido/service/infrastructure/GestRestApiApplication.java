package br.com.gestrest.pedido.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.pedido.service")
@EntityScan(basePackages = "br.com.gestrest.pedido.service")
@EnableJpaRepositories(basePackages = "br.com.gestrest.pedido.service")
public class GestRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestRestApiApplication.class, args);
	}

}
