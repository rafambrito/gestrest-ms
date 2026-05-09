package br.com.gestrest.pagamento.service.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.gestrest.pagamento.service")
@EntityScan(basePackages = "br.com.gestrest.pagamento.service")
@EnableJpaRepositories(basePackages = "br.com.gestrest.pagamento.service")
public class GestrestPagamentoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestrestPagamentoServiceApplication.class, args);
    }
}
