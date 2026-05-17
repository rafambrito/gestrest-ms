package br.com.gestrest.pagamento.service.adapters.out.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.gestrest.pagamento.service.adapters.out.integration.dto.ProcpagRequisicaoResponse;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayResponse;

@ExtendWith(MockitoExtension.class)
class PaymentGatewayAdapterTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    private PaymentGatewayAdapter adapter;

    @BeforeEach
    void setUp() {
        when(restTemplateBuilder.requestFactory(any(java.util.function.Supplier.class))).thenReturn(restTemplateBuilder);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        adapter = new PaymentGatewayAdapter(restTemplateBuilder, "http://localhost:8089", Duration.ofSeconds(5));
    }

    @Test
    void deveRetornarSucessoQuandoProcpagResponderComSucesso() {
        var requisicaoResponse = new ProcpagRequisicaoResponse("ext-123", "sucesso", null);
        when(restTemplate.postForObject(anyString(), any(), eq(ProcpagRequisicaoResponse.class)))
            .thenReturn(requisicaoResponse);
        when(restTemplate.getForObject(eq("http://localhost:8089/requisicao/ext-123"), eq(ProcpagRequisicaoResponse.class)))
            .thenReturn(requisicaoResponse);

        PagamentoGatewayResponse response = adapter.iniciarPagamento(1L, 1L, 1L, BigDecimal.valueOf(100));

        assertNotNull(response);
        assertEquals("ext-123", response.pagamentoIdExterno());
        assertEquals("sucesso", response.status());
    }

    @Test
    void deveRetornarPendenteQuandoGatewayFalharNaRequisicao() {
        when(restTemplate.postForObject(anyString(), any(), eq(ProcpagRequisicaoResponse.class)))
            .thenThrow(new RestClientException("erro de conexao"));

        PagamentoGatewayResponse response = adapter.iniciarPagamento(1L, 1L, 1L, BigDecimal.valueOf(100));

        assertNotNull(response);
        assertEquals("PENDENTE", response.status());
    }

    @Test
    void deveRetornarPendenteQuandoFallbackAsyncForChamadoPorTimeout() {
        PagamentoGatewayResponse response = adapter.fallbackPagamento(1L, 1L, 1L, BigDecimal.valueOf(100), new RuntimeException("timeout"));

        assertNotNull(response);
        assertEquals("PENDENTE", response.status());
    }

    @Test
    void deveRetornarPendenteQuandoFallbackForChamadoPorCircuitBreakerAberto() {
        var response = adapter.fallbackPagamentoAsync(1L, 1L, 1L, BigDecimal.valueOf(100), new RuntimeException("circuit open")).join();

        assertNotNull(response);
        assertEquals("PENDENTE", response.status());
    }
}
