package br.com.gestrest.pedido.service.adapters.in.web.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.gestrest.pedido.service.domain.model.PedidoStatusEnum;

public record PedidoResponse(Long id, Long usuarioId, Long restauranteId, BigDecimal valorTotal, PedidoStatusEnum status,
        List<ItemPedidoResponse> itens, LocalDateTime dataCriacao, LocalDateTime dataUltimaAlteracao) {
}
