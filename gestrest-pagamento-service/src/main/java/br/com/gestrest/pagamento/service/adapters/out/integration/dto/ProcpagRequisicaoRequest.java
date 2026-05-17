package br.com.gestrest.pagamento.service.adapters.out.integration.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcpagRequisicaoRequest {

    @JsonProperty("pagamento_id")
    private String pagamentoId;

    @JsonProperty("cliente_id")
    private String clienteId;

    @JsonProperty("valor")
    private BigDecimal valor;
}