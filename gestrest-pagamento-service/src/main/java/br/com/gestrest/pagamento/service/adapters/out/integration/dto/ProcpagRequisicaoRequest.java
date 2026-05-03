package br.com.gestrest.pagamento.service.adapters.out.integration.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcpagRequisicaoRequest {
    private Long pagamentoId;
    private Long clienteId;
    private BigDecimal valor;
}
