package br.com.gestrest.pagamento.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.pagamento.service.adapters.in.web.mapper.ConsultarPagamentoWebMapper;

@Configuration
public class MapperConfig {

    @Bean
    public ConsultarPagamentoWebMapper consultarPagamentoWebMapper() {
        return new ConsultarPagamentoWebMapper();
    }    
    
}
