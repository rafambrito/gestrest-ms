package br.com.gestrest.auth.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.auth.service")
@EntityScan(basePackages = "br.com.gestrest.auth.service")
@EnableJpaRepositories(basePackages = "br.com.gestrest.auth.service")
public class GestrestAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestrestAuthServiceApplication.class, args);
    }
}
