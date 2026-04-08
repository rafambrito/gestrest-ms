package br.com.gestrest.auth.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.auth.service")
public class GestrestAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestrestAuthServiceApplication.class, args);
    }
}
