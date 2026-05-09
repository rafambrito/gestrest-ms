package br.com.gestrest.restaurante.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.restaurante.service")
@EntityScan(basePackages = "br.com.gestrest.restaurante.service")
@EnableJpaRepositories(basePackages = "br.com.gestrest.restaurante.service")
public class GestrestRestauranteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestrestRestauranteServiceApplication.class, args);
    }
}
