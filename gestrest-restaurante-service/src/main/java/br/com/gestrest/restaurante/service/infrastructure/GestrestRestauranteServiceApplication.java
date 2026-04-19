package br.com.gestrest.restaurante.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.restaurante.service")
public class GestrestRestauranteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestrestRestauranteServiceApplication.class, args);
    }
}
