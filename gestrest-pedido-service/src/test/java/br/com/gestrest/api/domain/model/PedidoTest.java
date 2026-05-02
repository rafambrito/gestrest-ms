package br.com.gestrest.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.domain.exception.BusinessException;
import br.com.gestrest.pedido.service.domain.model.ItemPedido;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.PedidoStatusEnum;

@DisplayName("Pedido testes de dominio")
class PedidoTest {

    @Test
    void criarPedidoIniciaComStatusCriado() {
        var itens = List.of(ItemPedido.criar(1L, "Pizza", 2, new BigDecimal("45.90")));
        var pedido = Pedido.criar(1L, 10L, itens);

        assertEquals(PedidoStatusEnum.CRIADO, pedido.getStatus());
        assertEquals(1L, pedido.getUsuarioId());
        assertEquals(10L, pedido.getRestauranteId());
        assertEquals(0, pedido.getValorTotal().compareTo(new BigDecimal("91.80")));
        assertNotNull(pedido.getDataCriacao());
    }

    @Test
    void deveMarcarPedidoComoPagoQuandoPagamentoAprovado() {
        var itens = List.of(ItemPedido.criar(1L, "Pizza", 1, new BigDecimal("42.90")));
        var pedido = Pedido.criar(1L, 10L, itens);

        pedido.marcarPago();

        assertEquals(PedidoStatusEnum.PAGO, pedido.getStatus());
        assertNotNull(pedido.getDataUltimaAlteracao());
    }

    @Test
    void deveMarcarPedidoComoPendentePagamentoQuandoPagamentoFalha() {
        var itens = List.of(ItemPedido.criar(1L, "Pizza", 1, new BigDecimal("42.90")));
        var pedido = Pedido.criar(1L, 10L, itens);

        pedido.marcarPendentePagamento();

        assertEquals(PedidoStatusEnum.PENDENTE_PAGAMENTO, pedido.getStatus());
        assertNotNull(pedido.getDataUltimaAlteracao());
    }

    @Test
    void deveLancarExcecaoParaTransicaoInvalidaDeStatus() {
        var itens = List.of(ItemPedido.criar(1L, "Pizza", 1, new BigDecimal("42.90")));
        var pedido = Pedido.criar(1L, 10L, itens);

        pedido.marcarPago();

        assertThrows(BusinessException.class, pedido::marcarPendentePagamento);
    }
}
